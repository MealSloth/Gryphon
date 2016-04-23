package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.BlobRequest;
import com.mealsloth.gryphon.api.result.BlobResult;
import com.mealsloth.gryphon.models.BlobModel;
import com.mealsloth.gryphon.models.PostModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostDetailActivity extends AbstractNavBarActivity
{
    public static final String INTENT_POST = "post";

    private PostModel post;
    private ArrayList<BlobModel> blobs;

    private TextView tvPostName;
    private TextView tvPostDescription;
    private TextView tvTimeAvailable;
    private TextView tvTimeAvailableNext;

    private ImageView ivPost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_post_detail);
        super.onCreate(savedInstanceState);
        this.init();
        new BlobRequest()
                .activity(this)
                .methodBlobs(this.post.albumID, 1)
                .request();
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Received result progress");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        switch (methodName)
        {
            case BlobRequest.METHOD_BLOBS:
                BlobResult blobResult = new BlobResult(results);
                this.blobs = blobResult.blobs;
                if (this.blobs != null && this.blobs.size() > 0)
                    Picasso.with(this).load(this.blobs.get(0).url).into(this.ivPost);
                break;
        }
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
        this.ivPost = (ImageView)findViewById(R.id.activity_post_detail_iv_post);

        this.tvPostName.setText(this.post.name);
        this.tvPostDescription.setText(this.post.description);
        this.tvTimeAvailable.setText(this.post.postTime.substring(5,19).replace("T", " "));
        this.tvTimeAvailableNext.setText(this.post.expireTime.substring(5,19).replace("T", " "));
    }
}
