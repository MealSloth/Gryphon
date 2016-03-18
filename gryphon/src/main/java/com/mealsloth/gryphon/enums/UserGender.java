package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */
public class UserGender
{
    public enum UserGenderEnum
    {
        MALE (0),
        FEMALE (1),
        OTHER (2);

        public int index;
        UserGenderEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<UserGenderEnum, String> userGenderEnum;
    static
    {
        userGenderEnum = new HashMap<>();
        userGenderEnum.put(UserGenderEnum.MALE, "Male");
        userGenderEnum.put(UserGenderEnum.FEMALE, "Female");
        userGenderEnum.put(UserGenderEnum.OTHER, "Other");
    }

    public static String stringForInt(int index)
    {
        if (index < UserGenderEnum.values().length)
            return userGenderEnum.get(UserGender.forInt(index));
        else
            return null;
    }

    public static UserGenderEnum forInt(int index)
    {
        if (index < UserGenderEnum.values().length)
            return UserGenderEnum.values()[index];
        else
            return null;
    }
}
