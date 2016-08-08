package com.pos.aliong.aliongpos.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliong on 8/7/16.
 */
public class Basket {

    public interface OnBasketChangedListener {
        void onChanged();
    }

    private List<Item> items;

    private long totalTransaction;

    private OnBasketChangedListener mListener;

    public Basket() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        Item lastItemOnBasket;
        if (items.size() > 0 && (lastItemOnBasket = items.get(items.size() - 1)).equals(item)) {
            lastItemOnBasket.updateQty(lastItemOnBasket.getQty() + item.getQty());
        } else {
            items.add(item);
        }
        calculateTotalTransaction();
        notifyBasketHasChanged();
    }

    public void deleteItem(int index) {
        if (index > items.size() - 1) {
            throw new IllegalArgumentException("Index exceeding the limit");
        }
        items.remove(index);
        calculateTotalTransaction();
        notifyBasketHasChanged();
    }

    public List<Item> getItems() {
        return items;
    }

    public long getTotalTransaction() {
        return totalTransaction;
    }

    public void setOnBasketChangedListener(OnBasketChangedListener listener) {
        this.mListener = listener;
    }

    private void notifyBasketHasChanged() {
        if (mListener != null) {
            mListener.onChanged();
        }
    }

    private void calculateTotalTransaction() {
        totalTransaction = 0;
        if (items != null && items.size() > 0) {
            for (Item i : items) {
                totalTransaction += i.getTotalPrice();
            }
        }
    }
}
