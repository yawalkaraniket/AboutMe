package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.util.Log;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends BaseActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private FirebaseDatabase mFirebase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = mFirebase.getReference("UserInformation");
    SharedPreferencesUtil preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);

        this.preferences = new SharedPreferencesUtil(getApplicationContext());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<DataSnapshot> userInfo = new ArrayList<>();
                ArrayList<DataSnapshot> token = new ArrayList<>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    token.add(userSnapshot);
                    for (DataSnapshot programSnapshot : userSnapshot.getChildren()) {
                        userInfo.add(programSnapshot);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bottomNavigationSetUp(bottomNavigationView, this);
        navigationViewSetUp(navigationView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId()==R.id.home) {
            DialogUtil.yesDialog(this, "Close Application", "you want to close application?", click -> {
                this.finishAffinity();
            });
        } else {
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }
}
