package com.aboutme.avenjr.aboutme.model;

import com.aboutme.avenjr.aboutme.R;

public enum ProfileSelectionPagerModel {
    educational(R.string.education, R.layout.educational_pager),
    hobbies(R.string.hobby, R.layout.hobby_pager),
    otherActivities(R.string.other_activities, R.layout.other_activities);

    private int mTitleResId;
    private int mLayoutResId;

    ProfileSelectionPagerModel(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
