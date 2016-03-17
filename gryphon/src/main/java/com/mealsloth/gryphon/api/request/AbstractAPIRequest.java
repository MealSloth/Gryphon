package com.mealsloth.gryphon.api.request;

import android.content.Intent;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIService;

import java.util.HashMap;

/**
 * Created by michael on 3/16/16.
 */
public abstract class AbstractAPIRequest
{
    protected AbstractBaseActivity activity;
    protected String methodName;
    protected HashMap<String, String> parameters;

    public AbstractAPIRequest()
    {
        this.parameters = new HashMap<>();
    }

    public abstract AbstractAPIRequest activity(AbstractBaseActivity activity);

    public abstract String getCurrentClassName();

    public void request()
    {
        String className = this.getCurrentClassName();
        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this.activity, APIService.class);
        intent.putExtra(APIService.KEY_RECEIVER, this.activity.receiver);
        intent.putExtra(APIService.KEY_COMMAND, APIService.COMMAND_API_CALL);
        intent.putExtra(APIService.KEY_CLASS_NAME, className);
        intent.putExtra(APIService.KEY_METHOD_NAME, methodName);
        intent.putExtra(APIService.KEY_PARAMETERS, this.parameters);
        this.activity.startService(intent);
    }
}
