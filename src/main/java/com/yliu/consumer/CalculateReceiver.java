package com.yliu.consumer;

import com.yliu.bean.Asset;
import com.yliu.bean.SendParam;
import com.yliu.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "calculate")
public class CalculateReceiver {

    private final static Logger log = LoggerFactory.getLogger(CalculateReceiver.class);

    @Autowired
    private CalculateService calculateService;

    @RabbitHandler
    public void calculate(SendParam<Asset> sendParam){
        calculateService.calculateRate(sendParam.getParam());
        log.info("key = {} 的任务计算完毕",sendParam.getKey());
    }

    @RabbitHandler
    public void calculate(String msg){
        log.info("初始消息 {} ",msg);
    }
}
