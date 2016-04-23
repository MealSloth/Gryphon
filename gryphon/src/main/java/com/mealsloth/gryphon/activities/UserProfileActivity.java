package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.mealsloth.gryphon.R;

import java.util.ArrayList;

/**
 * Created by michael on 3/17/16.
 */
public class UserProfileActivity extends AbstractProfileActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_user_profile);
        super.onCreate(savedInstanceState);
        this.initViews();
    }

    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Received result progress in UserProfileActivity");
    }

    @Override
    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        super.handleReceiveResultFinished(results, methodName);
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received result error in UserProfileActivity");
    }

    protected void initViews()
    {
        this.tvName = (TextView)findViewById(R.id.tv_user_profile_name);
        this.tvGender = (TextView)findViewById(R.id.tv_user_profile_gender);
        this.tvDateOfBirth = (TextView)findViewById(R.id.tv_user_profile_date_of_birth);
    }
}
