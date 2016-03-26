package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class UserLoginModel extends AbstractAPIModel
{
    public String userID;
    public String username;
    private String password;
    private int accessLevel;

    public UserLoginModel(String id, String userID, String username, String password, int accessLevel)
    {
        super(id);
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public UserLoginModel(Parcel in)
    {
        super(in);
        this.userID = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.accessLevel = in.readInt();
    }

    public String getPassword()
    {
        return password;
    }

    public int getAccessLevel()
    {
        return accessLevel;
    }

    public int describeContents()
    {
        return super.describeContents();
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeString(this.userID);
        dest.writeString(this.username);
        dest.writeString(this.getPassword());
        dest.writeInt(this.getAccessLevel());
    }

    public static final Parcelable.Creator<UserLoginModel> CREATOR =
            new Parcelable.Creator<UserLoginModel>()
            {
                public UserLoginModel createFromParcel(Parcel in)
                {
                    return new UserLoginModel(in);
                }

                public UserLoginModel[] newArray(int size)
                {
                    return new UserLoginModel[size];
                }
            };
}
