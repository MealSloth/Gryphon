package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class PostModel
{
    public String id;
    public String chefID;
    public String locationID;
    public String albumID;
    public String name;
    public String description;
    public int orderCount;
    public int capacity;
    public int postStatus;
    public String postTime;
    public String expireTime;

    public PostModel(String id, String chefID, String locationID, String albumID, String name,
                     String description, int orderCount, int capacity, int postStatus,
                     String postTime, String expireTime)
    {
        this.id = id;
        this.chefID = chefID;
        this.locationID = locationID;
        this.albumID = albumID;
        this.name = name;
        this.description = description;
        this.orderCount = orderCount;
        this.capacity = capacity;
        this.postStatus = postStatus;
        this.postTime = postTime;
        this.expireTime = expireTime;
    }

    public PostModel(Parcel in)
    {
        this.id = in.readString();
        this.chefID = in.readString();
        this.locationID = in.readString();
        this.albumID = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.orderCount = in.readInt();
        this.capacity = in.readInt();
        this.postStatus = in.readInt();
        this.postTime = in.readString();
        this.expireTime = in.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
        dest.writeString(this.chefID);
        dest.writeString(this.locationID);
        dest.writeString(this.albumID);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.orderCount);
        dest.writeInt(this.capacity);
        dest.writeInt(this.postStatus);
        dest.writeString(this.postTime);
        dest.writeString(this.expireTime);
    }

    public static final Parcelable.Creator<PostModel> CREATOR =
            new Parcelable.Creator<PostModel>()
            {
                public PostModel createFromParcel(Parcel in)
                {
                    return new PostModel(in);
                }

                public PostModel[] newArray(int size)
                {
                    return new PostModel[size];
                }
            };
}
