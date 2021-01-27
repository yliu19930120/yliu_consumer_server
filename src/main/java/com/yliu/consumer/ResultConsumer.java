package com.yliu.consumer;


import com.yliu.bean.Asset;
import com.yliu.constant.RabbitQueue;
import com.yliu.constant.RabbitResult;
import com.yliu.service.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "RATE_CALCULATE_RESULT")
public class ResultConsumer {

    private final static Logger log = LoggerFactory.getLogger(ResultConsumer.class);


    @Autowired
    private RedisTemplate redisTemplate;


    @RabbitHandler
    public void calculate(Asset asset) throws IOException {
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(asset.getRequestId());
        Double value = (Double) boundValueOperations.get();
        log.info("得到计算结果 = {}",value);
        WebSocketServer.sendAllMessage(String.format("计算结果= %s",value));
    }
}
