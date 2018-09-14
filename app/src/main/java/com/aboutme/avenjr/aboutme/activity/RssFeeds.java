package com.aboutme.avenjr.aboutme.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aboutme.avenjr.aboutme.R;

import butterknife.ButterKnife;

public class RssFeeds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_feeds);
        ButterKnife.bind(this);
    }
}
