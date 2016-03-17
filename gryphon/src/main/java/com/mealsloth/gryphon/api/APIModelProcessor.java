package com.mealsloth.gryphon.api;

import com.mealsloth.gryphon.models.UserLoginModel;
import com.mealsloth.gryphon.models.UserModel;

import java.util.HashMap;

/**
 * Created by michael on 3/17/16.
 */
public class APIModelProcessor
{
    public static UserModel processUserModel(HashMap result)
    {
        return new UserModel(
                (String)result.get(APIParameter.PARAM_ID),
                (String)result.get(APIParameter.PARAM_SERVER_USER_LOGIN_ID),
                (String)result.get(APIParameter.PARAM_SERVER_CONSUMER_ID),
                (String)result.get(APIParameter.PARAM_SERVER_CHEF_ID),
                (String)result.get(APIParameter.PARAM_SERVER_LOCATION_ID),
                (String)result.get(APIParameter.PARAM_SERVER_BILLING_ID),
                (String)result.get(APIParameter.PARAM_SERVER_PROFILE_PHOTO_ID),
                (String)result.get(APIParameter.PARAM_EMAIL),
                (String)result.get(APIParameter.PARAM_SERVER_FIRST_NAME),
                (String)result.get(APIParameter.PARAM_SERVER_LAST_NAME),
                (String)result.get(APIParameter.PARAM_SERVER_PHONE_NUMBER),
                (String)result.get(APIParameter.PARAM_SERVER_DATE_OF_BIRTH),
                (int)result.get(APIParameter.PARAM_GENDER),
                (String)result.get(APIParameter.PARAM_SERVER_JOIN_DATE)
        );
    }

    public static UserLoginModel processUserLoginModel(HashMap result)
    {
        return new UserLoginModel(
                (String) result.get(APIParameter.PARAM_ID),
                (String) result.get(APIParameter.PARAM_SERVER_USER_ID),
                (String) result.get(APIParameter.PARAM_USERNAME),
                (String) result.get(APIParameter.PARAM_PASSWORD),
                (int) result.get(APIParameter.PARAM_SERVER_ACCESS_LEVEL)
        );
    }
}
