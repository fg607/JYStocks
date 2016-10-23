package com.hardwork.fg607.jystocks.model;

/**
 * Created by fg607 on 16-9-7.
 */
public class Stock {

    String name;
    String code;

    public Stock(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
