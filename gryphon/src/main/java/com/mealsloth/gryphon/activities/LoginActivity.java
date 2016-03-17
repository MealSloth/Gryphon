package com.mealsloth.gryphon.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.UserLoginRequest;
import com.mealsloth.gryphon.api.result.UserLoginResult;
import com.mealsloth.gryphon.models.UserLoginModel;

import java.util.ArrayList;

public class LoginActivity extends AbstractBaseActivity
{
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvError;

    private UserLoginModel userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.init();
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Progress received during login");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        switch (methodName)
        {
            case UserLoginRequest.METHOD_USER_LOGIN:
                UserLoginResult result = new UserLoginResult(results);
                this.userLogin = result.userLogin;
                authenticate();
                break;
        }
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Error occurred during login");
    }

    //Buttons
    public void login(View v)
    {
        this.tvError.setText("");
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        new UserLoginRequest()
                .activity(this)
                .methodUserLogin("ba5c2b50-3442-4a25-9c3e-2d2c42d5c4ac")
                .request();
    }

    public void toSignup(View v)
    {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        LoginActivity.this.startActivity(intent);
    }

    //Misc
    public void init()
    {
        this.etEmail = (EditText)findViewById(R.id.et_login_email);
        this.etPassword = (EditText)findViewById(R.id.et_login_password);
        this.tvError = (TextView)findViewById(R.id.tv_login_error);
    }

    public void authenticate()
    {
        if (this.userLogin.getPassword().equals(this.etPassword.getText().toString()))
        {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            LoginActivity.this.startActivity(intent);
        }
        else
        {
            this.tvError.setText(R.string.login_incorrect_credentials);
        }
    }
}
