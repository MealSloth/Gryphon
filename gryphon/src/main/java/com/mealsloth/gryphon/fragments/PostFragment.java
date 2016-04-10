package com.mealsloth.gryphon.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.models.PostModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#NewInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment
{
    private static final String ARG_POST = "post";

    private View fragmentView;

    private LinearLayout llMain;

    private TextView tvPostName;
    private TextView tvPostTime;
    private TextView tvPostReviews;

    private PostModel post;

    private OnFragmentInteractionListener listener;

    public PostFragment()
    {
        // Required empty public constructor
    }

    public static PostFragment NewInstance(PostModel post)
    {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_POST, post);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            this.post = this.getArguments().getParcelable(ARG_POST);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.fragmentView = inflater.inflate(R.layout.fragment_post, container, false);
        this.init();
        return this.fragmentView;
    }

    public void onPostClick(View v)
    {
        System.out.println("Clicked post with name: " + this.post.name);
        if (listener != null)
            listener.onFragmentInteraction(v);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
            listener = (OnFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(View v);
    }

    //Misc
    private void init()
    {
        this.llMain = (LinearLayout)this.fragmentView.findViewById(R.id.fragment_post_ll_main);
        this.llMain.setTag(this.post.id);

        this.tvPostName = (TextView)this.fragmentView.findViewById(R.id.tv_fragment_post_name);
        this.tvPostTime = (TextView)this.fragmentView.findViewById(R.id.tv_fragment_post_time);
        this.tvPostReviews = (TextView)this.fragmentView.findViewById(R.id.tv_fragment_post_reviews);

        this.tvPostName.setText(this.post.name);
        this.tvPostTime.setText(this.post.postTime.substring(11,19));
        this.tvPostReviews.setText("0 Reviews");
    }
}
