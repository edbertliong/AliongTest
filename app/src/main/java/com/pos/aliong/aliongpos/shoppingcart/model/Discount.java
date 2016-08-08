package com.pos.aliong.aliongpos.shoppingcart.model;

/**
 * Created by aliong on 8/7/16.
 */
public class Discount {

    private long id;

    private String name;

    private int percentage;

    public Discount(long id, String name, int percentage) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
