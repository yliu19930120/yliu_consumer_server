package com.yliu.controller;

import com.yliu.bean.Asset;
import com.yliu.bean.Result;
import com.yliu.service.CalculateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "计算")
@RestController
@ResponseBody
@RequestMapping("/calculate")
public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @ApiOperation(value = "区间收益率")
    @PostMapping("/returnrate")
    public Result<Double> calReturnrate(Asset asset){
        Double value = calculateService.calculateRate(asset);
        return Result.ok(value);
    }

}
