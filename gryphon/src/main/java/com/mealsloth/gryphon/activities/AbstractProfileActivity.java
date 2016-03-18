package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.UserRequest;
import com.mealsloth.gryphon.api.result.UserResult;
import com.mealsloth.gryphon.enums.UserGender;
import com.mealsloth.gryphon.models.UserModel;

import java.util.ArrayList;

/**
 * Created by michael on 3/17/16.
 */
public abstract class AbstractProfileActivity extends AbstractBaseActivity
{
    protected UserModel user;

    protected TextView tvName;
    protected TextView tvDateOfBirth;
    protected TextView tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new UserRequest()
                .activity(this)
                .methodUser("8bbfec5e-c29b-40d6-9918-45911e97134f")
                .request();
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        switch (methodName)
        {
            case UserRequest.METHOD_USER:
                UserResult userResult = new UserResult(results);
                this.user = userResult.user;
                this.tvName.setText(getString(
                        R.string.firstname_lastname,
                        this.user.firstName,
                        this.user.lastName
                ));
                this.tvGender.setText(UserGender.stringForInt(this.user.gender));
                this.tvDateOfBirth.setText(this.user.dateOfBirth.substring(0, 10));
                break;
        }
    }

    protected abstract void initViews();
}
