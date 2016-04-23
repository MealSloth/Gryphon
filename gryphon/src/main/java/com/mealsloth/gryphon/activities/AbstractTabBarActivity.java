package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mealsloth.gryphon.R;

/**
 * Created by michael on 4/22/16.
 */
public abstract class AbstractTabBarActivity extends AbstractNavBarActivity
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
        if (this.rootView == null)
            throw new RuntimeException("Children of AbstractTabBarActivity must use RelativeLayout "
            + "container with ID activity_tab_bar_rl_container");

        RelativeLayout.LayoutParams rvParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        rvParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        int bottom = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                50,
                getResources().getDisplayMetrics()
        );
        this.rootView.getChildAt(0).setPaddingRelative(0, 0, 0, bottom);
        this.rootView.addView(View.inflate(this, R.layout.tab_bar, null), rvParams);
    }

    //Buttons
    public void startDiscoverActivity(View v)
    {
        Toast.makeText(this, "Discover page not yet available", Toast.LENGTH_SHORT).show();
    }

    public void startHomeActivity(View v)
    {
        System.out.print("Home");
        if (!this.getClass().getCanonicalName().equals(HomeActivity.class.getCanonicalName()))
        {
            Intent intent = new Intent(AbstractTabBarActivity.this, HomeActivity.class);
            AbstractTabBarActivity.this.startActivity(intent);
        }
    }

    public void startProfileActivity(View v)
    {
        System.out.print("Profile");
        if (!this.getClass().getCanonicalName().equals(UserProfileActivity.class.getCanonicalName()))
        {
            Intent intent = new Intent(AbstractTabBarActivity.this, UserProfileActivity.class);
            AbstractTabBarActivity.this.startActivity(intent);
        }
    }
}