package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/24/16.
 */
public abstract class AbstractAPIModel implements Parcelable
{
    String id;

    public AbstractAPIModel(String id)
    {
        this.id = id;
    }

    public AbstractAPIModel(Parcel in)
    {
        this.id = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.id);
    }
}
