package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.models.BlobModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class BlobRequest extends AbstractAPIRequest
{
    public final static String METHOD_BLOB = "Blob";

    public BlobRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public String getCurrentClassName()
    {
        return BlobRequest.class.getCanonicalName();
    }

    public BlobRequest methodBlob(String blobID)
    {
        this.methodName = METHOD_BLOB;
        this.parameters.put(APIParameter.PARAM_CLIENT_BLOB_ID, blobID);
        return this;
    }

    public BlobModel Blob(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String blobID = (String)request.get(APIParameter.PARAM_CLIENT_BLOB_ID);
        data.put(APIParameter.PARAM_SERVER_BLOB_ID, blobID);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "blob/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_BLOB);
            return APIModelProcessor.ProcessBlobModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
