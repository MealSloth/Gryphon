package com.mealsloth.gryphon.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mealsloth.gryphon.R;
import com.mealsloth.gryphon.api.request.BlobRequest;
import com.mealsloth.gryphon.api.result.BlobResult;
import com.mealsloth.gryphon.models.BlobModel;
import com.mealsloth.gryphon.models.PostModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostFragment extends AbstractBaseFragment
{
    private static final String ARG_POST = "post";

    private String bannerText;

    private LinearLayout llMain;
    private RelativeLayout rlTop;

    private ImageView ivPost;

    private TextView tvPostName;
    private TextView tvPostTime;
    private TextView tvPostReviews;

    private PostModel post;
    private ArrayList<BlobModel> blobs;

    private OnFragmentInteractionListener listener;

    public PostFragment()
    {
        // Required empty public constructor
    }

    public static PostFragment NewInstance(PostModel post, String banner)
    {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_POST, post);
        fragment.setArguments(args);
        fragment.bannerText = banner;
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
        new BlobRequest()
                .fragment(this)
                .methodBlobs(this.post.albumID, 1)
                .request();
        return this.fragmentView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
            this.listener = (OnFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        this.listener = null;
    }

    //Results
    protected void handleReceiveResultProgress(ArrayList results, String methodName)
    {
        System.out.println("Received result progress during blob get");
    }

    protected void handleReceiveResultFinished(ArrayList results, String methodName)
    {
        switch (methodName)
        {
            case BlobRequest.METHOD_BLOBS:
                BlobResult blobResult = new BlobResult(results);
                this.blobs = blobResult.blobs;
                if (this.blobs != null && this.blobs.size() > 0)
                    Picasso.with(getContext()).load(this.blobs.get(0).url).into(this.ivPost);
                break;
        }
    }

    protected void handleReceiveResultError(ArrayList results, String methodName)
    {
        System.out.println("Received result error during blob get");
    }

    //Misc
    public void init()
    {
        this.llMain = (LinearLayout)this.fragmentView.findViewById(R.id.fragment_post_ll_main);
        this.rlTop = (RelativeLayout)this.fragmentView.findViewById(R.id.fragment_post_rl_top);

        this.ivPost = (ImageView)this.fragmentView.findViewById(R.id.fragment_post_iv_post);

        this.tvPostName = (TextView)this.fragmentView.findViewById(R.id.tv_fragment_post_name);
        this.tvPostTime = (TextView)this.fragmentView.findViewById(R.id.tv_fragment_post_time);
        this.tvPostReviews = (TextView)this.fragmentView.findViewById(R.id.tv_fragment_post_reviews);

        this.llMain.setTag(this.post.id);

        this.tvPostName.setText(this.post.name);
        this.tvPostTime.setText(this.post.postTime.substring(11,19));
        this.tvPostReviews.setText("1 Review");

        if (bannerText != null)
            this.addBanner(this.bannerText);
    }

    private void addBanner(String text)
    {
        TextView banner = new TextView(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 75);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        banner.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white_t80));
        banner.setText(text);
        banner.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22.0f);
        banner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        this.rlTop.addView(banner, params);
    }
}
