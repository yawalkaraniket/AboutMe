package com.aboutme.avenjr.aboutme.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        header.setView(getIntent().getStringExtra("header"),this);
    }
}
