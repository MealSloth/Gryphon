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
        intent.putExtra(APIService.KEY_COMMAND, "api_call");
        intent.putExtra(APIService.KEY_CLASS_NAME, className);
        intent.putExtra(APIService.KEY_METHOD_NAME, methodName);
        intent.putExtra(APIService.KEY_PARAMETERS, this.parameters);
        this.activity.startService(intent);
    }

    public UserLoginRequest methodUserLogin(String userLoginID)
    {
        this.methodName = "UserLogin";
        this.parameters.put("userLoginID", userLoginID);
        return this;
    }

    public UserLoginRequest methodUserLoginPasswordChange(String userLoginID, String password)
    {
        this.methodName = "UserLoginPasswordChange";
        this.parameters.put("userLoginID", userLoginID);
        this.parameters.put("password", password);
        return this;
    }

    /** @param request should contain either "userLoginID" or "userID" **/
    public UserLoginModel UserLogin(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String userLoginID = (String)request.get("userLoginID");
        String userID = (String)request.get("userID");
        if (userLoginID != null && !userLoginID.equals(""))
            data.put("user_login_id", userLoginID);
        else if (userID != null && !userID.equals(""))
            data.put("user_id", userID);

        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "user-login/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get("user_login");
            return new UserLoginModel(
                    (String)result.get("id"),
                    (String)result.get("user_id"),
                    (String)result.get("username"),
                    (String)result.get("password"),
                    (int)result.get("access_level")
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
        String userLoginID = (String)request.get("userLoginID");
        String password = (String)request.get("password");

        if (userLoginID != null && password != null)
        {
            data.put("user_login_id", userLoginID);
            data.put("password", password);
        }

        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "user-login/password/change/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get("user_login");
            return new UserLoginModel(
                    (String)result.get("id"),
                    (String)result.get("user_id"),
                    (String)result.get("username"),
                    (String)result.get("password"),
                    (int)result.get("access_level")
            );
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
