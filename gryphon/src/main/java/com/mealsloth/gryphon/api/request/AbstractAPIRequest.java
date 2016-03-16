package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;

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

    public abstract void request();
}
