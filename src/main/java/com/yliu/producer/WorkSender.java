package com.yliu.producer;

import com.yliu.bean.Asset;
import com.yliu.bean.SendParam;
import com.yliu.constant.RabbitQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WorkSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkSender.class);



}
