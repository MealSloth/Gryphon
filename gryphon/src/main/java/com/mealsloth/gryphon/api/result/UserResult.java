package com.mealsloth.gryphon.api.result;

import com.mealsloth.gryphon.models.UserLoginModel;
import com.mealsloth.gryphon.models.UserModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michael on 3/17/16.
 */
public class UserResult
{
    public UserModel user;
    public UserLoginModel userLogin;

    public UserResult(ArrayList results)
    {
        this.user = (UserModel)results.get(0);
    }

    public static UserResult UserCreateResult(ArrayList results)
    {
        HashMap result = (HashMap)results.get(0);
        ArrayList userArrayList = new ArrayList();
        userArrayList.add(result.get("user"));
        UserResult userResult = new UserResult(userArrayList);
        userResult.userLogin = (UserLoginModel)result.get("userLogin");
        return userResult;
    }
}
