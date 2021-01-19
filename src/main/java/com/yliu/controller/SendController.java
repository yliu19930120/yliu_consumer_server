package com.yliu.controller;


import com.yliu.bean.Result;
import com.yliu.bean.SendParam;
import com.yliu.producer.WorkSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "消息")
@RestController
@ResponseBody
@RequestMapping("/send")
public class SendController {

    @Autowired
    private WorkSender workSender;

    @ApiOperation(value = "消息发送的方法")
    @PostMapping
    public Result send(SendParam sendParam){

        try {
            workSender.send(sendParam);
        } catch (AmqpException e) {
            return Result.failue(e.getMessage());
        }

        return Result.ok();
    }

}
