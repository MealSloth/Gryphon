package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mealsloth.gryphon.R;

/**
 * Created by michael on 4/23/16.
 */
public abstract class AbstractNavBarActivity extends AbstractBaseActivity
{
    protected RelativeLayout rootView;

    private DrawerLayout drawerLayout;
    private ListView listView;

    private String[] menuItems;

    /**
     * Child classes should only call super.onCreate() after calling setContentView()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.initMenuItems();
        this.addNavBar();
        this.addNavigationDrawer();
    }

    private void initMenuItems()
    {
        this.menuItems = new String[]{
                "About",
                "Terms and Conditions",
                "Report a Bug",
                "Sign Out",
        };
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

    private void addNavigationDrawer()
    {
        this.rootView.addView(View.inflate(this, R.layout.fragment_navigation_drawer, null));

        this.drawerLayout = (DrawerLayout)this.findViewById(R.id.fragment_navigation_drawer_dl_root);
        this.listView = (ListView)this.findViewById(R.id.fragment_navigation_drawer_lv_menu);

        this.listView.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.navigation_drawer_list_item,
                this.menuItems
        ));

        this.listView.setOnItemClickListener(new DrawerItemClickListener());
    }

    //Listeners
    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView parent, View view, int index, long id)
        {
            handleClick(index);
        }
    }

    private void handleClick(int index)
    {
        System.out.println("Click at " + String.valueOf(index));
    }

    //Buttons
    public void startShoppingCartActivity(View v)
    {
        Intent intent = new Intent(AbstractNavBarActivity.this, ShoppingCartActivity.class);
        AbstractNavBarActivity.this.startActivity(intent);
    }

    public void toggleNavigationDrawer(View v)
    {
//        if (this.drawerLayout.isDrawerOpen(Gravity.LEFT))
//            this.drawerLayout.closeDrawer(Gravity.LEFT);
//        else
            this.drawerLayout.openDrawer(Gravity.LEFT);
    }
}