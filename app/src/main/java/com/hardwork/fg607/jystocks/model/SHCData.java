package com.hardwork.fg607.jystocks.model;

/**
 * Created by fg607 on 16-9-8.
 */
public class SHCData{

    float price;
    float change;
    float changeRatio;

    public SHCData(float price, float change, float changeRatio) {
        this.price = price;
        this.change = change;
        this.changeRatio = changeRatio;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public float getChangeRatio() {
        return changeRatio;
    }

    public void setChangeRatio(float changeRatio) {
        this.changeRatio = changeRatio;
    }
}
