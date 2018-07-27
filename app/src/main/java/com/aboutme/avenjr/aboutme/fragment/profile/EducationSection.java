package com.aboutme.avenjr.aboutme.fragment.profile;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.aboutme.avenjr.aboutme.Adapter.Profile.ProfileSectionAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EducationSection extends Fragment {

    @BindView(R.id.education_section)
    RecyclerView sectionRecyclerView;

    ArrayList<String> name;
    SharedPreferencesUtil preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_education_section, container, false);
        ButterKnife.bind(this,view);
        name = new ArrayList<>();
        name.add("first");
        name.add("Second");


        preferences = new SharedPreferencesUtil(getActivity().getApplicationContext());
        sectionRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ProfileSectionAdapter adapter = new ProfileSectionAdapter(name,preferences);
        sectionRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new RecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Add click on the button.
            }
        });
        return view;
    }
}
