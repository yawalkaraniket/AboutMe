package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import com.aboutme.avenjr.aboutme.R;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

     }
}
