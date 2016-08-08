package com.pos.aliong.aliongpos.shoppingcart.model;

import java.util.List;

/**
 * Created by aliong on 8/7/16.
 */
public class Item {

    private long id;

    private long variantId;

    private String name;

    private long price;

    private long qty;

    private List<Modifier> modifiers;

    private List<Discount> discounts;

    private long totalPrice;

    public Item(long id, long variantId, String name,
                long price, long qty, List<Modifier> modifiers, List<Discount> discounts) {

        this.id = id;
        this.variantId = variantId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.modifiers = modifiers;
        this.discounts = discounts;
        calculateTotalPrice();
    }

    public long getId() {
        return id;
    }

    public long getVariantId() {
        return variantId;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public long getQty() {
        return qty;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void updateVariant(long variantId, String name, long price) {
        this.variantId = variantId;
        this.name = name;
        this.price = price;
        calculateTotalPrice();
    }

    public void updateQty(long qty) {
        this.qty = qty;
        calculateTotalPrice();
    }

    public void updateModifiers(List<Modifier> modifiers) {
        this.modifiers = modifiers;
        calculateTotalPrice();
    }

    public void updateDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
        calculateTotalPrice();
    }

    public String getLabel() {
        String itemLabel = name;
        if (modifiers != null && modifiers.size() > 0) {
            for(Modifier m : modifiers) {
                itemLabel += ("\n" + m.getName());
            }
        }
        return itemLabel;
    }

    private void calculateTotalPrice() {
        long allModifierPrice = 0;
        if (modifiers != null && modifiers.size() > 0) {
            for(Modifier m : modifiers) {
                allModifierPrice += m.getPrice();
            }
        }

        long totalPercentageDiscount = 0;
        if (discounts != null && discounts.size() > 0) {
            for(Discount d : discounts) {
                totalPercentageDiscount += d.getPercentage();
            }

            if (totalPercentageDiscount > 100) {
                totalPercentageDiscount = 100;
            }
        }

        long priceWithModifier = price + allModifierPrice;
        long priceAfterDiscount = (long)(priceWithModifier - ((totalPercentageDiscount / 100.0) * priceWithModifier));

        totalPrice = qty * priceAfterDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (variantId != item.variantId) return false;
        if (price != item.price) return false;
        if (!name.equals(item.name)) return false;
        if (modifiers != null ? !modifiers.equals(item.modifiers) : item.modifiers != null)
            return false;
        return discounts != null ? discounts.equals(item.discounts) : item.discounts == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (variantId ^ (variantId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (modifiers != null ? modifiers.hashCode() : 0);
        result = 31 * result + (discounts != null ? discounts.hashCode() : 0);
        return result;
    }
}
