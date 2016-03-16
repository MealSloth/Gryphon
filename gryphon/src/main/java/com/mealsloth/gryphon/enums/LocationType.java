package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */
public class LocationType
{
    public enum LocationTypeEnum
    {
        Residential (0),
        Commercial (1);

        public int index;
        LocationTypeEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<LocationTypeEnum, String> locationTypeEnum;
    static
    {
        locationTypeEnum = new HashMap<>();
        locationTypeEnum.put(LocationTypeEnum.Residential, "Residential");
        locationTypeEnum.put(LocationTypeEnum.Commercial, "Commercial");
    }

    public static LocationTypeEnum forInt(int index)
    {
        if (index < LocationTypeEnum.values().length)
            return LocationTypeEnum.values()[index];
        else
            return null;
    }
}
