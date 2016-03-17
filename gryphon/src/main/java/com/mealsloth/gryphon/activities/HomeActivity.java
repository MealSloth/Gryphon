package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.PostRequest;
import com.mealsloth.gryphon.api.result.PostResult;
import com.mealsloth.gryphon.models.PostModel;

import java.util.ArrayList;

public class HomeActivity extends AbstractBaseActivity
{
    private PostModel post;
    private ArrayList<PostModel> posts;

    private TextView tvDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.init();

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
            case PostRequest.METHOD_POST:
                PostResult postResult = new PostResult(results);
                this.post = postResult.post;
                this.tvDebug.setText(this.post.id + "\n" + this.post.name);
                break;
            case PostRequest.METHOD_POST_PAGE:
                PostResult postsResult = new PostResult(results);
                this.posts = postsResult.posts;
                String debug = "";
                for (int i = 0; i < this.posts.size(); i++)
                    debug += this.posts.get(i).id + "\n" + this.posts.get(i).name + "\n";
                this.tvDebug.setText(debug);
        }
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received result error during post get");
    }

    //Misc
    private void init()
    {
        this.tvDebug = (TextView)findViewById(R.id.tv_home_debug);
    }
}
