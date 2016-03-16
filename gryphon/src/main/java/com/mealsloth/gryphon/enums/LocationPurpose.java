package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */
public class LocationPurpose
{
    public enum LocationPurposeEnum
    {
        Billing (0),
        Post (1),
        Display (2);

        public int index;
        LocationPurposeEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<LocationPurposeEnum, String> locationPurposeEnum;
    static
    {
        locationPurposeEnum = new HashMap<>();
        locationPurposeEnum.put(LocationPurposeEnum.Billing, "Billing");
        locationPurposeEnum.put(LocationPurposeEnum.Post, "Post");
        locationPurposeEnum.put(LocationPurposeEnum.Display, "Display");
    }

    public static LocationPurposeEnum forInt(int index)
    {
        if (index < LocationPurposeEnum.values().length)
            return LocationPurposeEnum.values()[index];
        else
            return null;
    }
}
