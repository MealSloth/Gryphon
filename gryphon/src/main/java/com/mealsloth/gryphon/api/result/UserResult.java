package com.mealsloth.gryphon.api.result;

import com.mealsloth.gryphon.models.UserModel;

import java.util.ArrayList;

/**
 * Created by michael on 3/17/16.
 */
public class UserResult
{
    public UserModel user;

    public UserResult(ArrayList results)
    {
        this.user = (UserModel)results.get(0);
    }
}
