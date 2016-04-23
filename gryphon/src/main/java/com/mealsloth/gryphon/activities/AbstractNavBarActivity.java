package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mealsloth.gryphon.R;

/**
 * Created by michael on 4/23/16.
 */
public abstract class AbstractNavBarActivity extends AbstractBaseActivity
{
    protected RelativeLayout rootView;

    /**
     * Child classes should only call super.onCreate() after calling setContentView()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.addNavBar();
    }

    private void addNavBar()
    {
        this.rootView = (RelativeLayout)this.findViewById(R.id.activity_nav_bar_rl_container);
        if (this.rootView == null)
            throw new RuntimeException("Children of AbstractNavBarActivity must use RelativeLayout "
                    + "container with ID activity_nav_bar_rl_container");

        RelativeLayout.LayoutParams rvParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        rvParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        int top = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                44,
                getResources().getDisplayMetrics()
        );
        this.rootView.getChildAt(0).setPaddingRelative(0, top, 0, 0);
        this.rootView.addView(View.inflate(this, R.layout.nav_bar, null), rvParams);
    }

    //Buttons
    public void startShoppingCartActivity(View v)
    {
        Intent intent = new Intent(AbstractNavBarActivity.this, ShoppingCartActivity.class);
        AbstractNavBarActivity.this.startActivity(intent);
    }


}
