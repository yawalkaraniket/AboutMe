package com.aboutme.avenjr.aboutme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.Adapter.ProfileAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.select_profile_recycler_view)
    RecyclerView selectProfileRecyclerView;

    @BindView(R.id.profile_parent_layout)
    RelativeLayout parentLayout;

    ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);

        data.add("first");
        data.add("second");

        header.setUp(this.getActivity());
        header.setView(getString(R.string.profile_header_string),this.getActivity());
        selectProfileRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        ProfileAdapter adapter = new ProfileAdapter(data);
        selectProfileRecyclerView.setAdapter(adapter);

        return view;
    }

    @OnClick(R.id.profile_parent_layout)
    public void click(){
        //  do nothing
    }
}
