package com.mealsloth.gryphon.api.request;

import android.content.Intent;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIService;
import com.mealsloth.gryphon.fragments.AbstractBaseFragment;

import java.util.HashMap;

/**
 * Created by michael on 3/16/16.
 */
public abstract class AbstractAPIRequest
{
    protected AbstractBaseFragment fragment;
    protected AbstractBaseActivity activity;
    protected String methodName;
    protected HashMap<String, String> parameters;

    public AbstractAPIRequest()
    {
        this.parameters = new HashMap<>();
    }

    public AbstractAPIRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public AbstractAPIRequest fragment(AbstractBaseFragment fragment)
    {
        this.fragment = fragment;
        this.activity = (AbstractBaseActivity)this.fragment.getActivity();
        return this;
    }

    public abstract String getCurrentClassName();

    public void request()
    {
        String className = this.getCurrentClassName();
        final Intent intent;
        intent = new Intent(Intent.ACTION_SYNC, null, this.activity, APIService.class);
        if (this.fragment != null)
            intent.putExtra(APIService.KEY_RECEIVER, this.fragment.receiver);
        else if (this.activity != null)
            intent.putExtra(APIService.KEY_RECEIVER, this.activity.receiver);
        else
            throw new RuntimeException("No valid context supplied for API Request");
        intent.putExtra(APIService.KEY_COMMAND, APIService.COMMAND_API_CALL);
        intent.putExtra(APIService.KEY_CLASS_NAME, className);
        intent.putExtra(APIService.KEY_METHOD_NAME, methodName);
        intent.putExtra(APIService.KEY_PARAMETERS, this.parameters);
        this.activity.startService(intent);
    }
}
