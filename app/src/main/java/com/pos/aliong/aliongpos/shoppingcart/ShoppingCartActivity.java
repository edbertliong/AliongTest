package com.pos.aliong.aliongpos.shoppingcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.pos.aliong.aliongpos.R;
import com.pos.aliong.aliongpos.shoppingcart.model.Basket;
import com.pos.aliong.aliongpos.shoppingcart.model.Item;
import com.pos.aliong.aliongpos.shoppingcart.model.Modifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliong on 8/8/16.
 */
public class ShoppingCartActivity extends AppCompatActivity implements
        View.OnClickListener, Basket.OnBasketChangedListener {

    private Button item1;
    private Button item2;
    private ListView listView;
    private ItemAdapter mAdapter;
    private Basket mBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        mBasket = new Basket();
        mBasket.setOnBasketChangedListener(this);
        item1 = (Button) findViewById(R.id.item1_btn);
        item2 = (Button) findViewById(R.id.item2_btn);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.list_item);
        mAdapter = new ItemAdapter(this, android.R.layout.simple_list_item_1, mBasket.getItems());
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == item1) {
            List<Modifier> modifiers = new ArrayList<>();
            Modifier modifier = new Modifier(1L, "Syrup", 5000L);
            modifiers.add(modifier);
            Item americano = new Item(1L, 1L, "Cafe Americano", 29000L, 2, modifiers, null);
            mBasket.addItem(americano);
        } else if (v == item2) {
            Item englishBreakfast = new Item(2L, 2L, "English Breakfast", 20000L, 1, null, null);
            mBasket.addItem(englishBreakfast);
        }
    }

    @Override
    public void onChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public class ItemAdapter extends ArrayAdapter<Item> {

        public ItemAdapter(Context context, int resource, List<Item> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) super.getView(position, convertView, parent);
            Item item = getItem(position);
            textView.setText(item.getLabel() + " (" + item.getQty() + ")");
            return textView;
        }
    }
}
