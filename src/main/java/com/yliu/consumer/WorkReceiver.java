package com.yliu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.util.StopWatch;

public class WorkReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkReceiver.class);

    private final int instance;

    public WorkReceiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", this.instance, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", this.instance, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
