package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.fragments.AbstractBaseFragment;
import com.mealsloth.gryphon.models.BlobModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class BlobRequest extends AbstractAPIRequest
{
    public final static String METHOD_BLOB = "Blob";
    public final static String METHOD_BLOBS = "Blobs";

    public final static int COUNT_ALL = -1;

    public BlobRequest activity(AbstractBaseActivity activity)
    {
        super.activity(activity);
        return this;
    }

    public BlobRequest fragment(AbstractBaseFragment fragment)
    {
        super.fragment(fragment);
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

    public BlobRequest methodBlobs(String albumID, int count)
    {
        this.methodName = METHOD_BLOBS;
        this.parameters.put(APIParameter.PARAM_CLIENT_ALBUM_ID, albumID);
        this.parameters.put(APIParameter.PARAM_COUNT, String.valueOf(count));
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
            result = (HashMap) result.get(APIParameter.PARAM_BLOB);
            return APIModelProcessor.ProcessBlobModel(result);
        }
        catch (IOException error)
        {
            return null;
        }

    }

    public ArrayList<BlobModel> Blobs(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String albumID = (String)request.get(APIParameter.PARAM_CLIENT_ALBUM_ID);
        String count = (String)request.get(APIParameter.PARAM_COUNT);
        data.put(APIParameter.PARAM_SERVER_ALBUM_ID, albumID);
        data.put(APIParameter.PARAM_COUNT, count);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "blob/", data).post();
            HashMap results = new ObjectMapper().readValue(response, HashMap.class);
            ArrayList<HashMap> blobs = (ArrayList<HashMap>)results.get(APIParameter.PARAM_BLOBS);
            ArrayList<BlobModel> processedBlobs = new ArrayList<>();
            for (int i = 0; i < blobs.size(); i++)
                processedBlobs.add(APIModelProcessor.ProcessBlobModel(blobs.get(i)));
            return processedBlobs;
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
