package com.aboutme.avenjr.aboutme.fragment.profile;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class HobbySection extends Fragment {

    @BindView(R.id.hobby_recycler_view)
    RecyclerView sectionRecyclerView;

    ArrayList<String> name;
    ProfileInfo profileInfo;
    SharedPreferencesUtil preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hobby_section, container, false);
        ButterKnife.bind(this,view);
        setHobbies();

        preferences = new SharedPreferencesUtil(getActivity().getApplicationContext());
        sectionRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ProfileSectionAdapter adapter = new ProfileSectionAdapter(name,preferences,getActivity());
        sectionRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Add click on the button.
            }
        });
        return view;
    }

    private void setHobbies() {
        name = new ArrayList<>();
        profileInfo = new ProfileInfo();
        profileInfo.setHobbies();

        name.addAll(profileInfo.getHobbies());
        for (String section : profileSections) {
            if(name.contains(section)){
                name.remove(section);
            }
        }

    }

}
