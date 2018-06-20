package com.aboutme.avenjr.aboutme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackFragment extends BaseFragment {

    @BindView(R.id.feedback_navigation)
    NavigationHeader header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        ButterKnife.bind(this,view);
        header.setUp(this.getActivity());
        header.setView("Feedback", this.getActivity());
        return view;
    }

}
