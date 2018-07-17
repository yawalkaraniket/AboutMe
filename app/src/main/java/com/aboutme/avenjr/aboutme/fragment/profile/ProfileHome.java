package com.aboutme.avenjr.aboutme.fragment.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.Adapter.ProfileAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileHome extends Fragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.select_profile_recycler_view)
    RecyclerView selectProfileRecyclerView;

    ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_home, container, false);
        ButterKnife.bind(this,view);

        data.add("first");
        data.add("second");

        header.setView(getString(R.string.profile_header_string),this.getActivity());
        selectProfileRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        ProfileAdapter adapter = new ProfileAdapter(data);
        selectProfileRecyclerView.setAdapter(adapter);
        return  view;
    }
}
