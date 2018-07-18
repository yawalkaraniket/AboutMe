package com.aboutme.avenjr.aboutme.Adapter.Profile;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.profile.EducationSection;
import com.aboutme.avenjr.aboutme.fragment.profile.EntertainmentSection;
import com.aboutme.avenjr.aboutme.fragment.profile.HobbySection;
import com.aboutme.avenjr.aboutme.fragment.profile.OtherActivitySection;

public class ProfileSelectorPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public ProfileSelectorPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EducationSection();
            case 1:
                return new HobbySection();
            case 2:
                return new OtherActivitySection();
            case 3:
                return new EntertainmentSection();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.education);
            case 1:
                return mContext.getString(R.string.hobby);
            case 2:
                return mContext.getString(R.string.other_activities);
            case 3:
                return mContext.getString(R.string.entertainment_section);
        }
        return null;
    }
}
