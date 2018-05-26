package com.aboutme.avenjr.aboutme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogFragment extends Fragment {

    @BindView(R.id.work_navigation)
    NavigationHeader header;

    @BindView(R.id.blog_body)
    RelativeLayout body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog_frgment, container, false);
        ButterKnife.bind(this,view);
        header.setUp(this.getActivity());
        header.setView("My Blog",this.getActivity());
        return view;
    }
}
