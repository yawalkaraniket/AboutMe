package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

class HomeScreen extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout homeScreenLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        homeScreenLayout = findViewById(R.id.content_frame);

        // Adding a navigation layout
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View navigaionHeader = layoutInflater.inflate(R.layout.fragment_navigation_header_home, homeScreenLayout,true);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
    }
}
