package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class AlbumModel extends AbstractAPIModel
{
    String time;

    public AlbumModel(String id, String time)
    {
        super(id);
        this.time = time;
    }

    private AlbumModel(Parcel in)
    {
        super(in);
        this.time = in.readString();
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
        dest.writeString(this.time);
    }

    public static final Parcelable.Creator<AlbumModel> CREATOR
            = new Parcelable.Creator<AlbumModel>() {
        public AlbumModel createFromParcel(Parcel in) {
            return new AlbumModel(in);
        }

        public AlbumModel[] newArray(int size) {
            return new AlbumModel[size];
        }
    };
}
