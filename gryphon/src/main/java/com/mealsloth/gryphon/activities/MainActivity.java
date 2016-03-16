package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.UserLoginRequest;
import com.mealsloth.gryphon.api.result.UserLoginResult;
import com.mealsloth.gryphon.models.UserLoginModel;

import java.util.ArrayList;

public class MainActivity extends AbstractBaseActivity
{
    private UserLoginModel userLogin;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tv = (TextView)findViewById(R.id.tv_test);

        new UserLoginRequest()
                .activity(this)
                .methodUserLoginPasswordChange("ba5c2b50-3442-4a25-9c3e-2d2c42d5c4ac", "testpass")
                .request();
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("MainActivity received progress update");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        switch (methodName)
        {
            case UserLoginRequest.METHOD_USER_LOGIN:
            case UserLoginRequest.METHOD_USER_LOGIN_PASSWORD_CHANGE:
                UserLoginResult result = new UserLoginResult(results);
                this.userLogin = result.userLogin;
                this.displayTestText();
                break;
        }
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Error occurred");
    }

    //Test
    private void displayTestText()
    {
        this.tv.setText(userLogin.id + "\n" +
                        userLogin.userID + "\n" +
                        userLogin.username + "\n" +
                        userLogin.getPassword() + "\n" +
                        userLogin.getAccessLevel()
        );
    }
}