package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.aboutme.avenjr.aboutme.Adapter.RecyclerViewAdapterExample;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

public class HomeScreen extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout homeScreenLayout;
    private Boolean mSlideState = false;
    private NavigationHeader mNavigationHeader;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    Activity mActivity;

    String data[] = {"sfsd", "dfsf", "sefsdfs", "sfsfsdf", "sfsdfsd", "sdfsdfsd",
            "dfsfsf", "sdfsfdsd", "dsdfsdf", "dsf", "sdfkjsdd", "kjsdfkjshd"};
    Button drawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        homeScreenLayout = findViewById(R.id.content_frame);
        drawerToggle = findViewById(R.id.drawer_layout_toggle);
        mNavigationHeader = findViewById(R.id.navigation_header);
        mRecyclerView = findViewById(R.id.home_page_recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);

        mNavigationHeader.setUp(this, "HomeScreen");
        mNavigationHeader.setView("HomeScreen");
        hideProgress(mProgressBar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerViewAdapterExample(data));

        navigationViewSetUp(mNavigationView, this);

        drawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()) {
                    Intent intent = new Intent(getBaseContext(), LearnKotlin.class);
                    startActivity(intent);
                    showProgress(mProgressBar);
                } else {
                    netWorkErrorDialog();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }
}
