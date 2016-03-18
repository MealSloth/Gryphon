package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.PostRequest;
import com.mealsloth.gryphon.api.result.PostResult;
import com.mealsloth.gryphon.fragments.PostFragment;
import com.mealsloth.gryphon.models.PostModel;

import java.util.ArrayList;

public class HomeActivity extends AbstractBaseFragmentActivity
{
    private ArrayList<PostModel> posts;

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
                for (int i = 0; i < this.posts.size(); i++)
                {
                    PostFragment postFragment = PostFragment.newInstance(this.posts.get(i));
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.ll_activity_home_container, postFragment).commit();
                }
        }
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received result error during post get");
    }

    public void onFragmentInteraction(Uri uri)
    {
        System.out.println("Interaction");
    }
}
