package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mealsloth.gryphon.R;

import java.util.ArrayList;

public class ShoppingCartActivity extends AbstractBaseActivity
{
    private TextView tvUnitPrice;
    private TextView tvTotalPrice;
    private TextView tvQuantity;

    private int unitPrice;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        this.init();
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Received result progress");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        System.out.println("Received result finished");
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received result error");
    }

    //Buttons
    public void increaseQuantity(View v)
    {
        this.quantity = (this.quantity < 99) ? this.quantity + 1 : 99;
        this.updateTextViews();
    }

    public void decreaseQuantity(View v)
    {
        this.quantity = (this.quantity > 0) ? this.quantity - 1 : 0;
        this.updateTextViews();
    }

    //Misc
    private void init()
    {
        this.unitPrice = 10;
        this.quantity = 1;

        this.tvQuantity = (TextView)findViewById(R.id.activity_shopping_cart_tv_order_sample_1_quantity);
        this.tvTotalPrice = (TextView)findViewById(R.id.activity_shopping_cart_tv_order_sample_1_total_price);
        this.tvUnitPrice = (TextView)findViewById(R.id.activity_shopping_cart_tv_order_sample_1_unit_price);

        this.updateTextViews();
    }

    private void updateTextViews()
    {
        this.tvQuantity.setText(String.valueOf(this.quantity));
        this.tvUnitPrice.setText("$" + String.valueOf(this.unitPrice) + ".00");
        this.tvTotalPrice.setText("$" + String.valueOf(this.quantity * this.unitPrice) + ".00");
    }
}
