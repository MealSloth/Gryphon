package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class ChefModel extends AbstractAPIModel
{
    String userID;
    String locationID;

    public ChefModel(String id, String userID, String locationID)
    {
        super(id);
        this.userID = userID;
        this.locationID = locationID;
    }

    private ChefModel(Parcel in)
    {
        super(in);
        this.userID = in.readString();
        this.locationID = in.readString();
    }

    @Override
    public int describeContents()
    {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeString(this.userID);
        dest.writeString(this.locationID);
    }

    public static final Parcelable.Creator<ChefModel> CREATOR
            = new Parcelable.Creator<ChefModel>() {
        public ChefModel createFromParcel(Parcel in) {
            return new ChefModel(in);
        }

        public ChefModel[] newArray(int size) {
            return new ChefModel[size];
        }
    };
}
