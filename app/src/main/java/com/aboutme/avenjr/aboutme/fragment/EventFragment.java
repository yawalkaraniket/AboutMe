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

public class EventFragment extends Fragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this,view);
        init();

        return view;
    }

    private void init() {
        header.setUp(this.getActivity());
        header.setView(getString(R.string.events_header_string),this.getActivity(),false);
    }

}
