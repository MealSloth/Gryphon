package com.mealsloth.gryphon.api;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by michael on 3/15/16.
 */
@SuppressLint("ParcelCreator")
public class APIResultReceiver extends ResultReceiver
{
    private Receiver receiver;

    public APIResultReceiver(Handler handler)
    {
        super(handler);
    }

    public void setReceiver(Receiver receiver)
    {
        this.receiver = receiver;
    }

    public interface Receiver
    {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData)
    {
        if (this.receiver != null)
            this.receiver.onReceiveResult(resultCode, resultData);
    }
}