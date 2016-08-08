package com.pos.aliong.aliongpos.shoppingcart.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by aliong on 8/7/16.
 */
public class ItemTest {

    @Test
    public void calculateTotalPrice_withNoModifiersNorDiscounts_shouldCalculateCorrectTotalPrice() {
        Item item = new Item(1L, 1L, "Cafe Americano", 29000L, 2, null, null);
        assertEquals(58000L, item.getTotalPrice());
    }

    @Test
    public void calculateTotalPrice_withModifiersButNoDiscounts_shouldCalculateCorrectTotalPrice() {
        List<Modifier> modifiers = new ArrayList<>();
        modifiers.add(new Modifier(1L, "Syrup", 5000L));
        modifiers.add(new Modifier(2L, "1 Shot Espresso", 10000L));

        Item item = new Item(1L, 1L, "Cafe Americano", 29000L, 2, modifiers, null);
        assertEquals(88000L, item.getTotalPrice());
    }

    @Test
    public void calculateTotalPrice_withModifiersAndDiscounts_shouldCalculateCorrectTotalPrice() {
        List<Modifier> modifiers = new ArrayList<>();
        modifiers.add(new Modifier(1L, "Syrup", 5000L));
        modifiers.add(new Modifier(2L, "1 Shot Espresso", 10000L));

        List<Discount> discounts =  new ArrayList<>();
        discounts.add(new Discount(1L, "Discount Hari Jumat", 10));
        discounts.add(new Discount(2L, "Discount Member", 5));

        Item item = new Item(1L, 1L, "Cafe Americano", 29000L, 2, modifiers, discounts);
        assertEquals(74800L, item.getTotalPrice());
    }
}
