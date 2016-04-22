package com.mealsloth.gryphon.api;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class APIService extends IntentService
{
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    public static final String KEY_RECEIVER = "receiver";
    public static final String KEY_CLASS_NAME = "className";
    public static final String KEY_METHOD_NAME = "methodName";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_PARAMETERS = "parameters";
    public static final String KEY_RESULTS = "results";

    public static final String COMMAND_API_CALL = "api_call";

    public APIService()
    {
        super("APIService");
    }

    protected void onHandleIntent(Intent intent)
    {
        final ResultReceiver receiver = intent.getParcelableExtra(KEY_RECEIVER);
        final String className = intent.getStringExtra(KEY_CLASS_NAME);
        final String methodName = intent.getStringExtra(KEY_METHOD_NAME);
        final String command = intent.getStringExtra(KEY_COMMAND);
        final HashMap parameter = (HashMap)intent.getSerializableExtra(KEY_PARAMETERS);
        Bundle b = new Bundle();
        if (command.equals(COMMAND_API_CALL))
        {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
            try
            {
                Class classObject = Class.forName(className);
                ArrayList results = new ArrayList();
                Method method = classObject.getMethod(methodName, HashMap.class);
                results.add(method.invoke(classObject.newInstance(), parameter));
                b.putSerializable(KEY_RESULTS, results);
                b.putString(KEY_METHOD_NAME, methodName);
                receiver.send(STATUS_FINISHED, b);
            }
            catch (Exception e)
            {
                b.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, b);
            }
        }
    }
}
