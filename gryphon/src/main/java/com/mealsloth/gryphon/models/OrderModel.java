package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class OrderModel extends AbstractAPIModel
{
    String id;
    String postID;
    String chefID;
    String consumerID;
    String locationID;
    String billingID;
    String orderTimeID;
    String orderSummaryID;
    int orderStatus;
    int orderType;
    int amount;

    public OrderModel(String id, String postID, String chefID, String consumerID, String locationID,
                      String billingID, String orderTimeID, String orderSummaryID, int orderStatus,
                      int orderType, int amount)
    {
        super(id);
        this.postID = postID;
        this.chefID = chefID;
        this.consumerID = consumerID;
        this.locationID = locationID;
        this.billingID = billingID;
        this.orderTimeID = orderTimeID;
        this.orderSummaryID = orderSummaryID;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.amount = amount;
    }

    public OrderModel(Parcel in)
    {
        super(in);
        this.postID = in.readString();
        this.chefID = in.readString();
        this.consumerID = in.readString();
        this.locationID = in.readString();
        this.billingID = in.readString();
        this.orderTimeID = in.readString();
        this.orderSummaryID = in.readString();
        this.orderStatus = in.readInt();
        this.orderType = in.readInt();
        this.amount = in.readInt();
    }

    public int describeContents()
    {
        return super.describeContents();
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeString(this.postID);
        dest.writeString(this.chefID);
        dest.writeString(this.consumerID);
        dest.writeString(this.locationID);
        dest.writeString(this.billingID);
        dest.writeString(this.orderTimeID);
        dest.writeString(this.orderSummaryID);
        dest.writeInt(this.orderStatus);
        dest.writeInt(this.orderType);
        dest.writeInt(this.amount);
    }

    public static final Parcelable.Creator<OrderModel> CREATOR =
            new Parcelable.Creator<OrderModel>()
            {
                public OrderModel createFromParcel(Parcel in)
                {
                    return new OrderModel(in);
                }

                public OrderModel[] newArray(int size)
                {
                    return new OrderModel[size];
                }
            };
}
