package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.models.UserLoginModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class UserLoginRequest extends AbstractAPIRequest
{
    public final static String METHOD_USER_LOGIN = "UserLogin";
    public final static String METHOD_USER_LOGIN_PASSWORD_CHANGE = "UserLoginPasswordChange";

    public UserLoginRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public String getCurrentClassName()
    {
        return UserLoginRequest.class.getCanonicalName();
    }

    public UserLoginRequest methodUserLogin(String userLoginID)
    {
        this.methodName = METHOD_USER_LOGIN;
        this.parameters.put(APIParameter.PARAM_CLIENT_USER_LOGIN_ID, userLoginID);
        return this;
    }

    public UserLoginRequest methodUserLoginPasswordChange(String userLoginID, String password)
    {
        this.methodName = METHOD_USER_LOGIN_PASSWORD_CHANGE;
        this.parameters.put(APIParameter.PARAM_CLIENT_USER_LOGIN_ID, userLoginID);
        this.parameters.put(APIParameter.PARAM_PASSWORD, password);
        return this;
    }

    /** @param request should contain either "userLoginID" or "userID" **/
    public UserLoginModel UserLogin(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String userLoginID = (String)request.get(APIParameter.PARAM_CLIENT_USER_LOGIN_ID);
        String userID = (String)request.get(APIParameter.PARAM_CLIENT_USER_ID);
        if (userLoginID != null && !userLoginID.equals(""))
            data.put(APIParameter.PARAM_SERVER_USER_LOGIN_ID, userLoginID);
        else if (userID != null && !userID.equals(""))
            data.put(APIParameter.PARAM_SERVER_USER_ID, userID);

        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "user-login/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_USER_LOGIN);
            return APIModelProcessor.ProcessUserLoginModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }

    public UserLoginModel UserLoginPasswordChange(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String userLoginID = (String)request.get(APIParameter.PARAM_CLIENT_USER_LOGIN_ID);
        String password = (String)request.get(APIParameter.PARAM_PASSWORD);

        if (userLoginID != null && password != null)
        {
            data.put(APIParameter.PARAM_SERVER_USER_LOGIN_ID, userLoginID);
            data.put(APIParameter.PARAM_PASSWORD, password);
        }

        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "user-login/password/change/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_USER_LOGIN);
            return APIModelProcessor.ProcessUserLoginModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
