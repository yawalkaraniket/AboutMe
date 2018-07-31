package com.aboutme.avenjr.aboutme.fragment.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.Adapter.Profile.ProfileSectionAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.data.ProfileInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aboutme.avenjr.aboutme.fragment.profile.ProfileHome.profileSections;

public class OtherActivitySection extends Fragment {


    @BindView(R.id.other_activity_recycler_view)
    RecyclerView sectionRecyclerView;

    ArrayList<String> name;
    SharedPreferencesUtil preferences;
    ProfileInfo profileInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_activity_section, container, false);
        ButterKnife.bind(this,view);
        setOtherActivities();

        preferences = new SharedPreferencesUtil(getActivity().getApplicationContext());
        sectionRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ProfileSectionAdapter adapter = new ProfileSectionAdapter(name,preferences);
        sectionRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Add click on the button.
            }
        });
        return view;
    }

    private void setOtherActivities() {
        name = new ArrayList<>();
        profileInfo = new ProfileInfo();
        profileInfo.setOtherActivities();

        name.addAll(profileInfo.getOtherActivities());

        for (String section : profileSections) {
            if(name.contains(section)){
                name.remove(section);
            }
        }

    }
}
