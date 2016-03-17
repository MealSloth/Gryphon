package com.mealsloth.gryphon.api.request;

import android.content.Intent;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIService;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
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

    public final static String PARAM_CLIENT_USER_LOGIN_ID = "userLoginID";
    public final static String PARAM_SERVER_USER_LOGIN_ID = "user_login_id";
    public final static String PARAM_CLIENT_USER_ID = "userID";
    public final static String PARAM_SERVER_USER_ID = "user_id";
    public final static String PARAM_USERNAME = "username";
    public final static String PARAM_PASSWORD = "password";
    public final static String PARAM_CLIENT_ACCESS_LEVEL = "accessLevel";
    public final static String PARAM_SERVER_ACCESS_LEVEL = "access_level";

    public final static String PARAM_USER_LOGIN = "user_login";

    public UserLoginRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public void request()
    {
        String className = UserLoginRequest.class.getCanonicalName();
        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this.activity, APIService.class);
        intent.putExtra(APIService.KEY_RECEIVER, this.activity.receiver);
        intent.putExtra(APIService.KEY_COMMAND, APIService.COMMAND_API_CALL);
        intent.putExtra(APIService.KEY_CLASS_NAME, className);
        intent.putExtra(APIService.KEY_METHOD_NAME, methodName);
        intent.putExtra(APIService.KEY_PARAMETERS, this.parameters);
        this.activity.startService(intent);
    }

    public UserLoginRequest methodUserLogin(String userLoginID)
    {
        this.methodName = METHOD_USER_LOGIN;
        this.parameters.put(PARAM_CLIENT_USER_LOGIN_ID, userLoginID);
        return this;
    }

    public UserLoginRequest methodUserLoginPasswordChange(String userLoginID, String password)
    {
        this.methodName = METHOD_USER_LOGIN_PASSWORD_CHANGE;
        this.parameters.put(PARAM_CLIENT_USER_LOGIN_ID, userLoginID);
        this.parameters.put(PARAM_PASSWORD, password);
        return this;
    }

    /** @param request should contain either "userLoginID" or "userID" **/
    public UserLoginModel UserLogin(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String userLoginID = (String)request.get(PARAM_CLIENT_USER_LOGIN_ID);
        String userID = (String)request.get(PARAM_CLIENT_USER_ID);
        if (userLoginID != null && !userLoginID.equals(""))
            data.put(PARAM_SERVER_USER_LOGIN_ID, userLoginID);
        else if (userID != null && !userID.equals(""))
            data.put(PARAM_SERVER_USER_ID, userID);

        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "user-login/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(PARAM_USER_LOGIN);
            return new UserLoginModel(
                    (String)result.get(PARAM_ID),
                    (String)result.get(PARAM_SERVER_USER_ID),
                    (String)result.get(PARAM_USERNAME),
                    (String)result.get(PARAM_PASSWORD),
                    (int)result.get(PARAM_SERVER_ACCESS_LEVEL)
            );
        }
        catch (IOException error)
        {
            return null;
        }
    }

    public UserLoginModel UserLoginPasswordChange(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String userLoginID = (String)request.get(PARAM_CLIENT_USER_LOGIN_ID);
        String password = (String)request.get(PARAM_PASSWORD);

        if (userLoginID != null && password != null)
        {
            data.put(PARAM_SERVER_USER_LOGIN_ID, userLoginID);
            data.put(PARAM_PASSWORD, password);
        }

        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "user-login/password/change/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(PARAM_USER_LOGIN);
            return new UserLoginModel(
                    (String)result.get(PARAM_ID),
                    (String)result.get(PARAM_SERVER_USER_ID),
                    (String)result.get(PARAM_USERNAME),
                    (String)result.get(PARAM_PASSWORD),
                    (int)result.get(PARAM_SERVER_ACCESS_LEVEL)
            );
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
