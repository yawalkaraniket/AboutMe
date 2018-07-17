package com.aboutme.avenjr.aboutme.fragment.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.Adapter.Profile.ProfileSelectorPagerAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.BaseFragment;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileSections extends BaseFragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.profile_selection_pager)
    ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_sections,
                container, false);
        ButterKnife.bind(this,view);

        pager.setAdapter(new ProfileSelectorPagerAdapter(getChildFragmentManager(),getContext()));
        header.setView(getString(R.string.profile_section),this.getActivity());

        return view;
    }

}
