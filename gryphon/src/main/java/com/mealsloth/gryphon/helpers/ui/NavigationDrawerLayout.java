package com.mealsloth.gryphon.helpers.ui;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by michael on 4/23/16.
 */
public class NavigationDrawerLayout extends DrawerLayout
{
    public NavigationDrawerLayout(Context context)
    {
        super(context);
    }

    public NavigationDrawerLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public NavigationDrawerLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.EXACTLY
        );
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec),
                MeasureSpec.EXACTLY
        );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
