package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */

public class UserLoginAccessLevel
{
    public enum UserLoginAccessLevelEnum
    {
        SuperAdmin (0),
        ChefAdmin (1),
        ConsumerAdmin (2),
        ConsumerChef (3),
        Chef (4),
        Consumer (5),
        Trial (6);

        public final int index;
        UserLoginAccessLevelEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<UserLoginAccessLevelEnum, String> userLoginAccessLevelEnum;
    static
    {
        userLoginAccessLevelEnum = new HashMap<>();
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.SuperAdmin, "Super Admin");
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.ChefAdmin, "Chef Admin");
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.ConsumerAdmin, "Consumer Admin");
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.ConsumerChef, "Consumer Chef");
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.Chef, "Chef");
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.Consumer, "Consumer");
        userLoginAccessLevelEnum.put(UserLoginAccessLevelEnum.Trial, "Trial");
    }

    public static UserLoginAccessLevelEnum forInt(int index)
    {
        if (index < UserLoginAccessLevelEnum.values().length)
            return UserLoginAccessLevelEnum.values()[index];
        else
            return null;
    }
}
