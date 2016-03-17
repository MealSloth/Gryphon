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
        UserResult result = UserResult.UserCreateResult(results);
        System.out.println("Received result with ULID: " + result.userLogin.id);
        System.out.println("Received result with UID: " + result.user.id);
        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
        SignupActivity.this.startActivity(intent);
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
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirm.getText().toString();
        if (email.equals("") || password.equals("") || passwordConfirm.equals(""))
            tvError.setText(R.string.signup_fields_required);
        else if (!email.contains("@"))
            tvError.setText(R.string.email_invalid);
        else if (!password.equals(passwordConfirm))
            tvError.setText(R.string.password_not_match);
        else if (password.length() < 8)
            tvError.setText(R.string.password_too_short);
        else
            new UserRequest()
                    .activity(this)
                    .methodUserCreate(email, password)
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
