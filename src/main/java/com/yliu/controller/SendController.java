package com.yliu.controller;


import com.yliu.bean.Asset;
import com.yliu.bean.Result;
import com.yliu.bean.SendParam;
import com.yliu.constant.RabbitQueue;
import com.yliu.producer.WorkSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(tags = "消息")
@RestController
@ResponseBody
@RequestMapping("/send")
public class SendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "消息发送的方法")
    @PostMapping
    public Result send(Asset asset){

        try {
            rabbitTemplate.convertAndSend(RabbitQueue.RATE_CALCULATE.getKey(),asset);
        } catch (AmqpException e) {
            return Result.failue(e.getMessage());
        }

        return Result.ok();
    }

}
