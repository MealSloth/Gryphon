package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.fragments.AbstractBaseFragment;
import com.mealsloth.gryphon.models.PostModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class PostRequest extends AbstractAPIRequest
{
    public final static String METHOD_POST = "Post";
    public final static String METHOD_POST_PAGE = "PostPage";

    public PostRequest activity(AbstractBaseActivity activity)
    {
        super.activity(activity);
        return this;
    }

    public PostRequest fragment(AbstractBaseFragment fragment)
    {
        super.fragment(fragment);
        return this;
    }

    public String getCurrentClassName() { return PostRequest.class.getCanonicalName(); }

    public PostRequest methodPost(String postID)
    {
        this.methodName = METHOD_POST;
        this.parameters.put(APIParameter.PARAM_CLIENT_POST_ID, postID);
        return this;
    }

    public PostRequest methodPostPage(int pageSize, String postTimeStamp)
    {
        this.methodName = METHOD_POST_PAGE;
        if (pageSize > 0)
            this.parameters.put(APIParameter.PARAM_CLIENT_PAGE_SIZE, String.valueOf(pageSize));
        if (postTimeStamp != null)
            this.parameters.put(APIParameter.PARAM_CLIENT_POST_TIME_STAMP, postTimeStamp);
        return this;
    }

    public PostModel Post(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String postID = (String)request.get(APIParameter.PARAM_CLIENT_POST_ID);
        data.put(APIParameter.PARAM_SERVER_POST_ID, postID);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "post/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_POST);
            return APIModelProcessor.ProcessPostModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }

    public ArrayList<PostModel> PostPage(HashMap request)
    {
        HashMap data = new HashMap<>();
        int pageSize = (request.get(APIParameter.PARAM_CLIENT_PAGE_SIZE) != null) ?
                (int)request.get(APIParameter.PARAM_CLIENT_PAGE_SIZE):
                0;
        String postTimeStamp = (request.get(APIParameter.PARAM_CLIENT_POST_TIME_STAMP) != null) ?
                (String)request.get(APIParameter.PARAM_CLIENT_POST_TIME_STAMP):
                null;
        if (pageSize > 0)
            data.put(APIParameter.PARAM_SERVER_PAGE_SIZE, pageSize);
        if (postTimeStamp != null)
            data.put(APIParameter.PARAM_CLIENT_POST_TIME_STAMP, postTimeStamp);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "post/page/", data).post();
            HashMap results = new ObjectMapper().readValue(response, HashMap.class);
            ArrayList<HashMap> posts = (ArrayList<HashMap>)results.get(APIParameter.PARAM_POSTS);
            ArrayList<PostModel> processedPosts = new ArrayList<>();
            for (int i = 0; i < posts.size(); i++)
                processedPosts.add(APIModelProcessor.ProcessPostModel(posts.get(i)));
            return processedPosts;
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
