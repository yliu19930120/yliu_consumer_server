package com.yliu.constant;

public enum RabbitQueue {

    RATE_CALCULATE("RATE_CALCULATE","区间收益率计算"),
    RATE_CALCULATE_RESULT("RATE_CALCULATE_RESULT","区间收益率计算结果")
    ;

    private String key;
    private String description;

    RabbitQueue(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
