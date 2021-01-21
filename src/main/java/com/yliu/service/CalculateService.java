package com.yliu.service;

import com.yliu.bean.Asset;
import com.yliu.bean.FundValueHis;
import com.yliu.bean.NetValue;
import com.yliu.bean.NetValueImpl;
import com.yliu.calculate.NetValueCalculator;
import com.yliu.calculate.ReturnrateCalculator;
import com.yliu.dao.FundValueHisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FundValueHisService fundValueHisService;

    public Double calculateRate(Asset asset){
        Query query=Query.query(Criteria.where("code").is(asset.getCode())
                .andOperator(Criteria.where("date").gte(asset.getStartDate()),
                        Criteria.where("date").lte(asset.getEndDate())))
                ;
        if(asset.getStartDate() == null && asset.getEndDate() == null){
            query=Query.query(Criteria.where("code").is(asset.getCode()));
        }
                ;
        List<FundValueHis> fundValueHis = mongoTemplate.find(query, FundValueHis.class);
        List<NetValue> netValues = fundValueHis.stream().map(t -> {
            NetValue netValue = new NetValueImpl(t.getValue(), t.getDate());
            netValue.setRate(t.getGrowthRate());
            return netValue;
        }).collect(Collectors.toList());
        NetValueCalculator calculator = new ReturnrateCalculator();
        return calculator.calculate(netValues);
    }

    public Double calculateRateInRedis(Asset asset){
        List<FundValueHis> fundValueHis = fundValueHisService.getByCode(asset.getCode());
        List<NetValue> netValues = fundValueHis.stream().map(t -> {
            NetValue netValue = new NetValueImpl(t.getValue(), t.getDate());
            netValue.setRate(t.getGrowthRate());
            return netValue;
        }).collect(Collectors.toList());
        NetValueCalculator calculator = new ReturnrateCalculator();
        return calculator.calculate(netValues);
    }
}
