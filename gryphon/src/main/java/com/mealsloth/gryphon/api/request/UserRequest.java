package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.api.result.APIResultProcessor;
import com.mealsloth.gryphon.models.UserModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class UserRequest extends AbstractAPIRequest
{
    public final static String METHOD_USER = "User";
    public final static String METHOD_USER_CREATE = "UserCreate";
    public final static String METHOD_USER_MODIFY = "UserModify";

    public UserRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public String getCurrentClassName()
    {
        return UserRequest.class.getCanonicalName();
    }

    public UserRequest methodUser(String userID)
    {
        this.methodName = METHOD_USER;
        this.parameters.put(APIParameter.PARAM_CLIENT_USER_ID, userID);
        return this;
    }

    public UserRequest methodUserCreate(String email, String password)
    {
        this.methodName = METHOD_USER_CREATE;
        this.parameters.put(APIParameter.PARAM_EMAIL, email);
        this.parameters.put(APIParameter.PARAM_PASSWORD, password);
        return this;
    }

    public UserModel User(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String userID = (String)request.get(APIParameter.PARAM_CLIENT_USER_ID);
        data.put(APIParameter.PARAM_SERVER_USER_ID, userID);
        try
        {
            String response = new JsonPost(APIHost.APIHostEnum.CHIMERA, "user/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_USER);
            return APIResultProcessor.processUserModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }

    public HashMap UserCreate(HashMap request)
    {
        return new HashMap();
    }
}
