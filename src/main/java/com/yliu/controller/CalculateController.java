package com.yliu.controller;

import com.yliu.bean.Asset;
import com.yliu.bean.FundValueHis;
import com.yliu.bean.Result;
import com.yliu.service.CalculateService;
import com.yliu.service.FundValueHisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "计算")
@RestController
@ResponseBody
@RequestMapping("/calculate")
public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @Autowired
    private FundValueHisService fundValueHisService;

    @ApiOperation(value = "区间收益率")
    @PostMapping("/returnrate")
    public Result<Double> calReturnrate(Asset asset){
        Double value = calculateService.calculateRate(asset);
        return Result.ok(value);
    }

    @ApiOperation(value = "同步净值")
    @GetMapping("/sync")
    public Result syncValue2Redis(){
        fundValueHisService.syncToRedis();
        return Result.ok();
    }

    @ApiOperation(value = "获取净值")
    @PostMapping("/values")
    public Result getValuesByCode(@RequestParam("code") String code){
        List<FundValueHis> byCode = fundValueHisService.getByCode(code);
        return Result.ok(byCode);
    }
}
