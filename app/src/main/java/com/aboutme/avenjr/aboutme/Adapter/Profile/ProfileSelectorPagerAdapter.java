package com.aboutme.avenjr.aboutme.Adapter.Profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.model.ProfileSelectionPagerModel;

public class ProfileSelectorPagerAdapter extends PagerAdapter{

    Context context;

    public ProfileSelectorPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ProfileSelectionPagerModel model = ProfileSelectionPagerModel.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(model.getLayoutResId(), container, false);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return ProfileSelectionPagerModel.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        ProfileSelectionPagerModel customPagerEnum = ProfileSelectionPagerModel.values()[position];
        return context.getString(customPagerEnum.getTitleResId());
    }
}
