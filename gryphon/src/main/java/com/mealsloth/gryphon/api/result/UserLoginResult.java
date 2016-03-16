package com.mealsloth.gryphon.api.result;

import com.mealsloth.gryphon.models.UserLoginModel;

import java.util.ArrayList;

/**
 * Created by michael on 3/16/16.
 */
public class UserLoginResult
{
    public UserLoginModel userLogin;

    public UserLoginResult(ArrayList results)
    {
        this.userLogin = (UserLoginModel)results.get(0);
    }
}
