package com.mealsloth.gryphon.enums;

import java.util.HashMap;

/**
 * Created by michael on 3/13/16.
 */
public class OrderStatus
{
    public enum OrderStatusEnum
    {
        Pending (0),
        Received (1),
        Progress (2),
        Completed (3),
        Shipped (4),
        Delivered (5),
        Disputed (6),
        Cancelled (7);

        public int index;
        OrderStatusEnum(int index)
        {
            this.index = index;
        }
    }

    public static final HashMap<OrderStatusEnum, String> orderStatusEnum;
    static
    {
        orderStatusEnum = new HashMap<>();
        orderStatusEnum.put(OrderStatusEnum.Pending, "Pending");
        orderStatusEnum.put(OrderStatusEnum.Received, "Received");
        orderStatusEnum.put(OrderStatusEnum.Progress, "Progress");
        orderStatusEnum.put(OrderStatusEnum.Completed, "Completed");
        orderStatusEnum.put(OrderStatusEnum.Shipped, "Shipped");
        orderStatusEnum.put(OrderStatusEnum.Delivered, "Delivered");
        orderStatusEnum.put(OrderStatusEnum.Disputed, "Disputed");
        orderStatusEnum.put(OrderStatusEnum.Cancelled, "Cancelled");
    }

    public static OrderStatusEnum forInt(int index)
    {
        if (index < OrderStatusEnum.values().length)
            return OrderStatusEnum.values()[index];
        else
            return null;
    }
}
