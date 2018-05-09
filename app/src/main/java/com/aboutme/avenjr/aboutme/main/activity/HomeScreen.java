package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.main.Adapter.RecyclerViewAdapterExample;
import com.aboutme.avenjr.aboutme.main.view.NavigationHeader;

public class HomeScreen extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout homeScreenLayout;
    private Boolean mSlideState = false;
    private NavigationHeader mNavigationHeader;
    private RecyclerView mRecyclerView;

    String data[] = {"sfsd","dfsf","sefsdfs","sfsfsdf","sfsdfsd","sdfsdfsd",
            "dfsfsf","sdfsfdsd","dsdfsdf","dsf","sdfkjsdd","kjsdfkjshd"};
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

        mNavigationHeader.setUp(this, "HomeScreen");
        mNavigationHeader.setView("HomeScreen");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerViewAdapterExample(data));
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });

        drawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LearnKotlin.class);
                startActivity(intent);
            }
        });

    }
}
