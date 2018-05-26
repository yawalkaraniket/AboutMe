package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.ProgressBar;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.DialogUtil;

import butterknife.ButterKnife;

public class HomeScreen extends BaseActivity {

    private NavigationView mNavigationView;
    private ProgressBar mProgressBar;

    private int backPressCount = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_home_screen);
        mNavigationView = findViewById(R.id.nav_view);
        mProgressBar = findViewById(R.id.progress_bar);
        hideProgress(mProgressBar);

        navigationViewSetUp(mNavigationView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (backPressCount == 2) {
            DialogUtil.yesDialog(this, "Close Application", "you want to close application?", click -> {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            });
        } else {
            backPressCount++;
        }
    }
}
