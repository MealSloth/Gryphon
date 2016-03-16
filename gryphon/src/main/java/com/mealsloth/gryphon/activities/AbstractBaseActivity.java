package com.mealsloth.gryphon.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mealsloth.gryphon.api.APIResultReceiver;
import com.mealsloth.gryphon.api.APIResultReceiver.Receiver;
import com.mealsloth.gryphon.api.APIService;

import java.util.ArrayList;

/**
 * Created by michael on 3/16/16.
 */
public abstract class AbstractBaseActivity extends AppCompatActivity implements Receiver
{
    public APIResultReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.receiver = new APIResultReceiver(new Handler());
        this.receiver.setReceiver(this);
    }

    @Override
    protected void onPause()
    {
        this.receiver.setReceiver(null);
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        this.receiver.setReceiver(this);
        super.onResume();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData)
    {
        ArrayList results = (ArrayList)resultData.get(APIService.KEY_RESULTS);
        String methodName = (String)resultData.get(APIService.KEY_METHOD_NAME);

        if (resultCode == APIService.STATUS_RUNNING)
        {
            this.handleReceiveResultProgress(results, methodName);
        }
        else if (resultCode == APIService.STATUS_FINISHED)
        {
            this.handleReceiveResultFinished(results, methodName);
        }
        else if (resultCode == APIService.STATUS_ERROR)
        {
            this.handleReceiveResultError(results, methodName);
        }
        else
        {
            System.out.println("Untracked result code");
        }
    }

    protected abstract void handleReceiveResultProgress(ArrayList results, String methodName);

    protected abstract void handleReceiveResultFinished(ArrayList results, String methodName);

    protected abstract void handleReceiveResultError(ArrayList results, String methodName);
}
