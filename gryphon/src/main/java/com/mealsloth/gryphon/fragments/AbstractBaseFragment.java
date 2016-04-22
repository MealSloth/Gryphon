package com.mealsloth.gryphon.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.APIResultReceiver;
import com.mealsloth.gryphon.api.APIResultReceiver.Receiver;
import com.mealsloth.gryphon.api.APIService;

import java.util.ArrayList;

/**
 * Created by michael on 4/20/16.
 */
public abstract class AbstractBaseFragment extends Fragment implements Receiver
{
    public APIResultReceiver receiver;
    protected View fragmentView;
    protected OnFragmentInteractionListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.receiver = new APIResultReceiver(new Handler());
        this.receiver.setReceiver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.fragmentView = inflater.inflate(R.layout.fragment_post, container, false);
        this.init();
        return this.fragmentView;
    }

    @Override
    public void onPause()
    {
        this.receiver.setReceiver(null);
        super.onPause();
    }

    @Override
    public void onResume()
    {
        this.receiver.setReceiver(this);
        super.onResume();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
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

    public abstract void init();

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(View v);
    }
}
