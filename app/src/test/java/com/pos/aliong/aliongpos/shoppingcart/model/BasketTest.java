package com.pos.aliong.aliongpos.shoppingcart.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by aliong on 8/8/16.
 */
public class BasketTest {

    @Test
    public void addItem_whenBasketEmpty_shouldAddTheItem() {
        Basket basket = new Basket();
        Item item = new Item(1L, 1L, "Cafe Americano", 30000L, 3, null, null);
        basket.addItem(item);
        assertEquals(1, basket.getItems().size());
        assertEquals(90000L, basket.getTotalTransaction());
    }

    @Test
    public void addItem_whenLastItemInBasketDifferent_shouldAddTheItem() {
        Basket basket = new Basket();
        Item item = new Item(1L, 1L, "Cafe Americano", 30000L, 3, null, null);
        Item item2 = new Item(2L, 2L, "English Breakfast", 15000L, 2, null, null);
        basket.addItem(item);
        basket.addItem(item2);
        assertEquals(2, basket.getItems().size());
        assertEquals(120000L, basket.getTotalTransaction());
    }

    @Test
    public void addItem_whenLastItemInBasketSame_shouldUpdateTheLastItem() {
        Basket basket = new Basket();
        Item item = new Item(1L, 1L, "Cafe Americano", 30000L, 3, null, null);
        Item item2 = new Item(1L, 1L, "Cafe Americano", 30000L, 2, null, null);
        basket.addItem(item);
        basket.addItem(item2);
        assertEquals(1, basket.getItems().size());
        assertEquals(150000L, basket.getTotalTransaction());
        assertEquals(5, basket.getItems().get(0).getQty());
    }

    @Test
    public void deleteItem_whenIndexExists_shouldRemoveItem() {
        Basket basket = new Basket();
        Item item = new Item(1L, 1L, "Cafe Americano", 30000L, 3, null, null);
        Item item2 = new Item(2L, 2L, "English Breakfast", 15000L, 2, null, null);
        basket.addItem(item);
        basket.addItem(item2);
        basket.deleteItem(0);
        assertEquals(1, basket.getItems().size());
        assertEquals(30000L, basket.getTotalTransaction());
    }
}
