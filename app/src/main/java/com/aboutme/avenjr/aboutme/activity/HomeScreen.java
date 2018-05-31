package com.aboutme.avenjr.aboutme.activity;

import android.content.Intent;
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            });
        } else {
            backPressCount++;
        }
    }
}
