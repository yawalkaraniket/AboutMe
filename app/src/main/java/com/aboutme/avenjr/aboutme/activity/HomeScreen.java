package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.util.Log;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.fragment.HomeFragment;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends BaseActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    SharedPreferencesUtil preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        replaceFragment(new HomeFragment());

        this.preferences = new SharedPreferencesUtil(getApplicationContext());

        bottomNavigationSetUp(bottomNavigationView, this);
        navigationViewSetUp(navigationView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.home) {
            DialogUtil.yesDialog(this, "Close Application", "you want to close application?", click -> {
                this.finishAffinity();
            });
        } else {
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }
}
