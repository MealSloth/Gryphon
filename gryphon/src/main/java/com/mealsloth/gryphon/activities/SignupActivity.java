package com.mealsloth.gryphon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.UserRequest;
import com.mealsloth.gryphon.api.result.UserResult;

import java.util.ArrayList;

public class SignupActivity extends AbstractBaseActivity
{
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.init();
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Received progress update during signup");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        UserResult result = new UserResult(results);
        this.tvError.setText(result.user.id + "\n" + result.user.firstName + "\n" + result.user.lastName);
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received error during signup");
    }

    //Buttons
    public void backToLogin(View v)
    {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        SignupActivity.this.startActivity(intent);
    }

    public void signup(View v)
    {
        this.tvError.setText("");
        new UserRequest()
                .activity(this)
                .methodUser("57a8e6c8-dc7e-461d-9854-8a96bd88e4b7")
                .request();
    }

    //Misc
    public void init()
    {
        this.etEmail = (EditText)findViewById(R.id.et_signup_email);
        this.etPassword = (EditText)findViewById(R.id.et_signup_password);
        this.etPasswordConfirm = (EditText)findViewById(R.id.et_signup_password_confirm);
        this.tvError = (TextView)findViewById(R.id.tv_signup_error);
    }
}
