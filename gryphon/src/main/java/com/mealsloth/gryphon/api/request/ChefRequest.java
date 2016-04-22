package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.fragments.AbstractBaseFragment;
import com.mealsloth.gryphon.models.ChefModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class ChefRequest extends AbstractAPIRequest
{
    public final static String METHOD_CHEF = "Chef";

    public ChefRequest activity(AbstractBaseActivity activity)
    {
        super.activity(activity);
        return this;
    }

    public ChefRequest fragment(AbstractBaseFragment fragment)
    {
        super.fragment(fragment);
        return this;
    }

    public String getCurrentClassName()
    {
        return ChefRequest.class.getCanonicalName();
    }

    public ChefRequest methodChef(String chefID)
    {
        this.methodName = METHOD_CHEF;
        this.parameters.put(APIParameter.PARAM_CLIENT_CHEF_ID, chefID);
        return this;
    }

    public ChefModel Chef(HashMap request)
    {
        HashMap<String , String> data = new HashMap<>();
        String chefID = (String)request.get(APIParameter.PARAM_CLIENT_CHEF_ID);
        data.put(APIParameter.PARAM_SERVER_CHEF_ID, chefID);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "chef/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_CHEF);
            return APIModelProcessor.ProcessChefModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
