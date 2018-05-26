package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.Mpin;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeScreenActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView mImageView;

    @BindView(R.id.mPinHeader)
    NavigationHeader header;

    @BindView(R.id.layout_mpin)
    Mpin pin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ButterKnife.bind(this);

        header.setUp(this);
        header.setView("mPin", this);

    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
