package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.aboutme.avenjr.aboutme.Adapter.ProfileAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.data.ProfileInfo;

import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

     }
}
