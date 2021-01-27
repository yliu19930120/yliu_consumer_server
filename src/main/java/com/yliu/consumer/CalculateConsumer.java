package com.yliu.consumer;

import com.yliu.bean.Asset;
import com.yliu.constant.RabbitQueue;
import com.yliu.constant.RabbitResult;
import com.yliu.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "RATE_CALCULATE")
public class CalculateConsumer {

    private final static Logger log = LoggerFactory.getLogger(CalculateConsumer.class);

    @Autowired
    private CalculateService calculateService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void calculate(Asset asset){
        Double aDouble = calculateService.calculateRateInRedis(asset);
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(asset.getRequestId());
        boundValueOperations.set(aDouble);

        rabbitTemplate.convertAndSend(RabbitQueue.RATE_CALCULATE_RESULT.getKey(), asset);
        log.info("的任务计算结果 = {}",aDouble);
    }

}
