package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */
public class PostStatus
{
    public enum PostStatusEnum
    {
        Active (0),
        Saturated (1),
        Inactive (2);

        public int index;
        PostStatusEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<PostStatusEnum, String> postStatusEnum;
    static
    {
        postStatusEnum = new HashMap<>();
        postStatusEnum.put(PostStatusEnum.Active, "Active");
        postStatusEnum.put(PostStatusEnum.Saturated, "Saturated");
        postStatusEnum.put(PostStatusEnum.Inactive, "Inactive");
    }

    public static PostStatusEnum forInt(int index)
    {
        if (index < PostStatusEnum.values().length)
            return PostStatusEnum.values()[index];
        else
            return null;
    }
}
