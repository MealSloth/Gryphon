package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.BlobRequest;
import com.mealsloth.gryphon.api.request.PostRequest;
import com.mealsloth.gryphon.api.result.BlobResult;
import com.mealsloth.gryphon.api.result.PostResult;
import com.mealsloth.gryphon.fragments.PostFragment;
import com.mealsloth.gryphon.models.PostModel;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class HomeActivity extends AbstractBaseFragmentActivity implements PostFragment.OnFragmentInteractionListener
{
    private ArrayList<PostModel> posts;
    private DateTime lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new PostRequest()
                .activity(this)
                .methodPostPage(-1, null)
                .request();
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Received result progress during post get");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        switch (methodName)
        {
            case PostRequest.METHOD_POST_PAGE:
                PostResult postsResult = new PostResult(results);
                this.posts = postsResult.posts;
                if (this.posts != null)
                    for (int i = 0; i < this.posts.size(); i++)
                        this.addFragment(i);
                break;
        }
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received result error during post get");
    }

    public void onFragmentInteraction(View v)
    {
        PostModel post = null;
        String postId = (String)v.getTag();
        for (PostModel pm : this.posts)
        {
            if (pm.id.equals(postId))
            {
                post = pm;
                break;
            }
        }
        if (post != null)
        {
            Intent intent = new Intent(HomeActivity.this, PostDetailActivity.class);
            intent.putExtra(PostDetailActivity.INTENT_POST, post);
            HomeActivity.this.startActivity(intent);
        }
    }

    //Misc
    public void startShoppingCartActivity(View v)
    {
        Intent intent = new Intent(HomeActivity.this, ShoppingCartActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    private void addFragment(int index)
    {
        String banner = getBanner(index);
        PostFragment postFragment = PostFragment.NewInstance(this.posts.get(index), banner);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.ll_activity_home_container, postFragment).commit();
    }

    private String getBanner(int index)
    {
        String result = null;
        DateTime newDateTime = DateTime.parse(this.posts.get(index).expireTime);
        int newTime = newDateTime.dayOfYear().get();
        if ((this.lastTime != null && this.lastTime.dayOfYear().get() > newTime) || index == 0)
            result = this.getMonthForInt(newDateTime.getMonthOfYear()) + " " + newDateTime.getDayOfMonth();
        this.lastTime = newDateTime;
        return result;
    }

    private String getMonthForInt(int monthInt)
    {
        String monthString = "wrong";
        String[] monthsArray = new DateFormatSymbols().getMonths();
        if (monthInt >= 0 && monthInt <= 11)
            monthString = monthsArray[monthInt];
        return monthString;
    }
}
