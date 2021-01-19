package com.yliu.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Asset implements Serializable {

    private static final long serialVersionUID = 4277871444305355605L;
    private String code;

    private LocalDate startDate;
    private LocalDate endDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
