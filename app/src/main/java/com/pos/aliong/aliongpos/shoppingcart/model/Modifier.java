package com.pos.aliong.aliongpos.shoppingcart.model;

/**
 * Created by aliong on 8/7/16.
 */
public class Modifier {

    private long id;

    private String name;

    private long price;

    public Modifier(long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
