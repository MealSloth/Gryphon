package com.mealsloth.gryphon.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michael on 3/13/16.
 */
public class UserModel extends AbstractAPIModel
{
    public String userLoginID;
    public String consumerID;
    public String chefID;
    public String locationID;
    public String billingID;
    public String profilePhotoID;
    public String email;
    public String firstName;
    public String lastName;
    private String phoneNumber;
    public String dateOfBirth;
    public int gender;
    public String joinDate;

    public UserModel(String id, String userLoginID, String consumerID, String chefID,
                     String locationID, String billingID, String profilePhotoID, String email,
                     String firstName, String lastName, String phoneNumber, String dateOfBirth,
                     int gender, String joinDate)
    {
        super(id);
        this.userLoginID = userLoginID;
        this.consumerID = consumerID;
        this.chefID = chefID;
        this.locationID = locationID;
        this.billingID = billingID;
        this.profilePhotoID = profilePhotoID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.joinDate = joinDate;
    }

    public UserModel(Parcel in)
    {
        super(in);
        this.userLoginID = in.readString();
        this.consumerID = in.readString();
        this.chefID = in.readString();
        this.locationID = in.readString();
        this.billingID = in.readString();
        this.profilePhotoID = in.readString();
        this.email = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.phoneNumber = in.readString();
        this.dateOfBirth = in.readString();
        this.gender = in.readInt();
        this.joinDate = in.readString();
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public int describeContents()
    {
        return super.describeContents();
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);
        dest.writeString(this.userLoginID);
        dest.writeString(this.consumerID);
        dest.writeString(this.chefID);
        dest.writeString(this.locationID);
        dest.writeString(this.billingID);
        dest.writeString(this.profilePhotoID);
        dest.writeString(this.email);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.dateOfBirth);
        dest.writeInt(this.gender);
        dest.writeString(this.joinDate);
    }

    public static final Parcelable.Creator<UserModel> CREATOR =
            new Parcelable.Creator<UserModel>()
            {
                public UserModel createFromParcel(Parcel in)
                {
                    return new UserModel(in);
                }

                public UserModel[] newArray(int size)
                {
                    return new UserModel[size];
                }
            };
}
