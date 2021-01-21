package com.yliu.service;

import com.yliu.bean.Fund;
import com.yliu.bean.FundValueHis;
import com.yliu.controller.SendController;
import com.yliu.dao.FundDao;
import com.yliu.dao.FundValueHisDao;
import com.yliu.utils.ScoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FundValueHisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FundDao fundDao;
    @Autowired
    private FundValueHisDao fundValueHisDao;

    @Async
    public void syncToRedis(){
        List<Fund> funds = fundDao.findAll();

        funds = funds.stream().sorted(Comparator.comparing(Fund::getCode)).limit(100).collect(Collectors.toList());

        funds.forEach(fund->{
            String code = fund.getCode();
            if(code==null){
                return;
            }
            List<FundValueHis> fundValues = fundValueHisDao.findAllByCode(code);
            if(CollectionUtils.isEmpty(fundValues)){
                return;
            }
            Set<ZSetOperations.TypedTuple<FundValueHis>> collect = fundValues.stream().map(t -> {
                ZSetOperations.TypedTuple<FundValueHis> typedTuple = new DefaultTypedTuple<>(t, ScoreUtils.date2Score(t.getDate()));
                return typedTuple;
            }).collect(Collectors.toSet());
            redisTemplate.delete(code);
            BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(code);
            boundZSetOperations.expire(1, TimeUnit.HOURS);
            boundZSetOperations.add(collect);
            LOGGER.info("基金{} 行情 写入数据{} 条",code,fundValues.size());
        });
    }

    public List<FundValueHis> getByCode(String code){
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(code);
        Set<ZSetOperations.TypedTuple<FundValueHis>> set = boundZSetOperations.rangeWithScores(0, -1);
        List<FundValueHis> fundValues = set.stream().map(ZSetOperations.TypedTuple::getValue).collect(Collectors.toList());
        LOGGER.info("读出 {} 记录 {}条",code,fundValues.size());
        return fundValues;
    }
}
