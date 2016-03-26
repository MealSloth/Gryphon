package com.mealsloth.gryphon.api;

import com.mealsloth.gryphon.models.AlbumModel;
import com.mealsloth.gryphon.models.BlobModel;
import com.mealsloth.gryphon.models.ChefModel;
import com.mealsloth.gryphon.models.ConsumerModel;
import com.mealsloth.gryphon.models.PostModel;
import com.mealsloth.gryphon.models.UserLoginModel;
import com.mealsloth.gryphon.models.UserModel;

import java.util.HashMap;

/**
 * Created by michael on 3/17/16.
 */
public class APIModelProcessor
{
    public static UserModel ProcessUserModel(HashMap result)
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

    public static UserLoginModel ProcessUserLoginModel(HashMap result)
    {
        return new UserLoginModel(
                (String) result.get(APIParameter.PARAM_ID),
                (String) result.get(APIParameter.PARAM_SERVER_USER_ID),
                (String) result.get(APIParameter.PARAM_USERNAME),
                (String) result.get(APIParameter.PARAM_PASSWORD),
                (int) result.get(APIParameter.PARAM_SERVER_ACCESS_LEVEL)
        );
    }

    public static PostModel ProcessPostModel(HashMap result)
    {
        return new PostModel(
                (String)result.get(APIParameter.PARAM_ID),
                (String)result.get(APIParameter.PARAM_SERVER_CHEF_ID),
                (String)result.get(APIParameter.PARAM_SERVER_LOCATION_ID),
                (String)result.get(APIParameter.PARAM_SERVER_ALBUM_ID),
                (String)result.get(APIParameter.PARAM_NAME),
                (String)result.get(APIParameter.PARAM_DESCRIPTION),
                (int)result.get(APIParameter.PARAM_SERVER_ORDER_COUNT),
                (int)result.get(APIParameter.PARAM_CAPACITY),
                (int)result.get(APIParameter.PARAM_SERVER_POST_STATUS),
                (String)result.get(APIParameter.PARAM_SERVER_POST_TIME),
                (String)result.get(APIParameter.PARAM_SERVER_EXPIRE_TIME)
        );
    }

    public static AlbumModel ProcessAlbumModel(HashMap result)
    {
        return new AlbumModel(
                (String)result.get(APIParameter.PARAM_ID),
                (String)result.get(APIParameter.PARAM_TIME)
        );
    }

    public static ChefModel ProcessChefModel(HashMap result)
    {
        return new ChefModel(
                (String)result.get(APIParameter.PARAM_ID),
                (String)result.get(APIParameter.PARAM_SERVER_USER_ID),
                (String)result.get(APIParameter.PARAM_SERVER_LOCATION_ID)
        );
    }

    public static BlobModel ProcessBlobModel(HashMap result)
    {
        return new BlobModel(
                (String)result.get(APIParameter.PARAM_ID),
                (String)result.get(APIParameter.PARAM_SERVER_ALBUM_ID),
                (String)result.get(APIParameter.PARAM_SERVER_GCS_ID),
                (String)result.get(APIParameter.PARAM_SERVER_CONTENT_TYPE),
                (String)result.get(APIParameter.PARAM_TIME)
        );
    }
}
