package com.aboutme.avenjr.aboutme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.Mpin;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aboutme.avenjr.aboutme.activity.SignUpActivity.setMpin;

public class WelcomeScreenActivity extends BaseActivity {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ButterKnife.bind(this);

        header.setUp(this);
        header.setView("mPin", this);

        if (setMpin) {
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

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}
