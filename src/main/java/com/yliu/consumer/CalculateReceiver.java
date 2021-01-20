package com.yliu.consumer;

import com.yliu.bean.Asset;
import com.yliu.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "RATE_CALCULATE")
public class CalculateReceiver {

    private final static Logger log = LoggerFactory.getLogger(CalculateReceiver.class);

    @Autowired
    private CalculateService calculateService;

    @RabbitHandler
    public void calculate(Asset asset){
        Double aDouble = calculateService.calculateRate(asset);
        log.info("的任务计算结果 = {}",aDouble);
    }

}
