package com.yliu.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "fundValueHis")
public class FundValueHis extends Bean implements Serializable {

    private static final long serialVersionUID = 6978880089999315958L;
    private String code;
    private Double value;
    private LocalDate date;
    private Double growthRate;
    private Double cumulativeValue;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }

    public Double getCumulativeValue() {
        return cumulativeValue;
    }

    public void setCumulativeValue(Double cumulativeValue) {
        this.cumulativeValue = cumulativeValue;
    }
}
