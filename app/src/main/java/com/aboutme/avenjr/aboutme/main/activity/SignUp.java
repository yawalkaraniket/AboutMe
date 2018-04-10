package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;

public class SignUp extends AppCompatActivity {

    private RelativeLayout signUpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //  Adding a views
        signUpView = findViewById(R.id.signup_view);


        //  Adding the back header
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View backHeader = layoutInflater.inflate(R.layout.backheader,signUpView,true);
    }
}
