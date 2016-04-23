package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mealsloth.gryphon.R;

/**
 * Created by michael on 4/22/16.
 */
public abstract class AbstractTabBarActivity extends AbstractBaseActivity
{
    protected RelativeLayout rootView;

    /**
     * Child classes should only call super.onCreate() after calling setContentView()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.addTabBar();
    }

    private void addTabBar()
    {
        this.rootView = (RelativeLayout)this.findViewById(R.id.activity_tab_bar_rl_container);

        RelativeLayout.LayoutParams rvParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        rvParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        this.rootView.addView(View.inflate(this, R.layout.tab_bar, null), rvParams);
    }
}