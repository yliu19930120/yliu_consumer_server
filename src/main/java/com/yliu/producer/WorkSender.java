package com.yliu.producer;

import com.yliu.bean.SendParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkSender.class);

    @Autowired
    private RabbitTemplate template;

    public void send(SendParam sendParam) throws AmqpException{
        template.convertAndSend(sendParam.getKey(), sendParam.getParam());
        LOGGER.info(" [x] Sent '{}'", sendParam.getParam());
    }

}
