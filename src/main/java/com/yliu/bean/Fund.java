package com.yliu.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("fund")
public class Fund extends Bean implements Serializable {

    private static final long serialVersionUID = -4412643676958682082L;
    private String code;
    private String pinyin;
    private String fullPinyin;
    private String fundType;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFullPinyin() {
        return fullPinyin;
    }

    public void setFullPinyin(String fullPinyin) {
        this.fullPinyin = fullPinyin;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
