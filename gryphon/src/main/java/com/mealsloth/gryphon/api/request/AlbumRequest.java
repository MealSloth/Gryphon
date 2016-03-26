package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.models.AlbumModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class AlbumRequest extends AbstractAPIRequest
{
    public final static String METHOD_ALBUM = "Album";

    public AlbumRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public String getCurrentClassName()
    {
        return AlbumRequest.class.getCanonicalName();
    }

    public AlbumRequest methodAlbum(String albumID)
    {
        this.methodName = METHOD_ALBUM;
        this.parameters.put(APIParameter.PARAM_CLIENT_ALBUM_ID, albumID);
        return this;
    }

    public AlbumModel Album(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String albumID = (String)request.get(APIParameter.PARAM_CLIENT_ALBUM_ID);
        data.put(APIParameter.PARAM_SERVER_ALBUM_ID, albumID);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "album/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_ALBUM);
            return APIModelProcessor.ProcessAlbumModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
