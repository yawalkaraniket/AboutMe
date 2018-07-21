package com.aboutme.avenjr.aboutme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.view.Mpin;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MpinActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView mImageView;

    @BindView(R.id.mPinHeader)
    NavigationHeader header;

    @BindView(R.id.layout_mpin)
    Mpin pin;

    @BindView(R.id.mpin_title)
    TextView mPinTitle;

    @BindView(R.id.mpin_email)
    TextView mPinEmail;

    @BindView(R.id.set_mpin)
    TextView setMpinText;
    SharedPreferencesUtil preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin_screen);
        ButterKnife.bind(this);

        header.setUp(this);
        header.setView("mPin", this,false);
        preferences = new SharedPreferencesUtil(getApplicationContext());

        if (preferences.getMPin().equals(getResources().getString(R.string.mpin))) {
            setMpinText.setVisibility(View.VISIBLE);
            mPinTitle.setVisibility(View.GONE);
            mPinEmail.setVisibility(View.GONE);
        }else{
            mPinTitle.setVisibility(View.VISIBLE);
            mPinEmail.setVisibility(View.VISIBLE);
            setMpinText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
