package com.mealsloth.gryphon.api.result;

import com.mealsloth.gryphon.models.PostModel;

import java.util.ArrayList;

/**
 * Created by michael on 3/17/16.
 */
public class PostResult
{
    public PostModel post;
    public ArrayList<PostModel> posts;

    public PostResult(ArrayList results)
    {
        if (results.get(0) instanceof PostModel)
            this.post = (PostModel)results.get(0);
        else
            this.posts = (ArrayList<PostModel>)results.get(0);
    }
}
