package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class BlobModel extends AbstractAPIModel
{
    String albumID;
    String gcsID;
    String contentType;
    String time;

    public BlobModel(String id, String albumID, String gcsID, String contentType, String time)
    {
        super(id);
        this.albumID = albumID;
        this.gcsID = gcsID;
        this.contentType = contentType;
        this.time = time;
    }

    private BlobModel(Parcel in)
    {
        super(in);
        this.albumID = in.readString();
        this.gcsID = in.readString();
        this.contentType = in.readString();
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

    public static final Parcelable.Creator<BlobModel> CREATOR
            = new Parcelable.Creator<BlobModel>() {
        public BlobModel createFromParcel(Parcel in) {
            return new BlobModel(in);
        }

        public BlobModel[] newArray(int size) {
            return new BlobModel[size];
        }
    };
}
