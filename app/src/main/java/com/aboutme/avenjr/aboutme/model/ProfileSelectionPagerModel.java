package com.aboutme.avenjr.aboutme.model;

import com.aboutme.avenjr.aboutme.R;

public enum ProfileSelectionPagerModel {
    RED(R.color.blue, R.layout.EducationalPager),
    BLUE(R.color.pink, R.layout.HobbyPager);

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
