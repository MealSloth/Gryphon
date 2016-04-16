package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.models.PostModel;

import java.util.ArrayList;

public class PostDetailActivity extends AbstractBaseActivity
{
    public static final String INTENT_POST = "post";

    private PostModel post;

    private TextView tvPostName;
    private TextView tvPostDescription;
    private TextView tvTimeAvailable;
    private TextView tvTimeAvailableNext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
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

    //Misc
    private void init()
    {
        Intent intent = this.getIntent();
        if (intent.hasExtra(PostDetailActivity.INTENT_POST))
            this.post = intent.getParcelableExtra(PostDetailActivity.INTENT_POST);
        else
            throw new RuntimeException("No post included in intent");

        this.tvPostName = (TextView)findViewById(R.id.activity_post_detail_tv_post_name);
        this.tvPostDescription = (TextView)findViewById(R.id.activity_post_detail_tv_post_description);
        this.tvTimeAvailable = (TextView)findViewById(R.id.activity_post_detail_tv_time_available);
        this.tvTimeAvailableNext = (TextView)findViewById(R.id.activity_post_detail_tv_time_available_next);

        this.tvPostName.setText(this.post.name);
        this.tvPostDescription.setText(this.post.description);
        this.tvTimeAvailable.setText(this.post.postTime.substring(5,19).replace("T", " "));
        this.tvTimeAvailableNext.setText(this.post.expireTime.substring(5,19).replace("T", " "));
    }

    public void startShoppingCartActivity(View v)
    {
        Intent intent = new Intent(PostDetailActivity.this, ShoppingCartActivity.class);
        PostDetailActivity.this.startActivity(intent);
    }
}
