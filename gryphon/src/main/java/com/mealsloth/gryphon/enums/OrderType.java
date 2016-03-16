package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */
public class OrderType
{
    public enum OrderTypeEnum
    {
        Pickup (0),
        Delivery (1),
        DineIn (2);

        public int index;
        OrderTypeEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<OrderTypeEnum, String> orderTypeEnum;
    static
    {
        orderTypeEnum = new HashMap<>();
        orderTypeEnum.put(OrderTypeEnum.Pickup, "Pickup");
        orderTypeEnum.put(OrderTypeEnum.Delivery, "Delivery");
        orderTypeEnum.put(OrderTypeEnum.DineIn, "Dine In");
    }

    public static OrderTypeEnum forInt(int index)
    {
        if (index < OrderTypeEnum.values().length)
            return OrderTypeEnum.values()[index];
        else
            return null;
    }
}
