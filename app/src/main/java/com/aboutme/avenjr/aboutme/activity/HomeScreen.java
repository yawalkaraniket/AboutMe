package com.aboutme.avenjr.aboutme.activity;

import android.content.Intent;
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

import butterknife.ButterKnife;

public class HomeScreen extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private FirebaseDatabase mFirebase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = mFirebase.getReference("UserInformation");
    private int backPressCount = 1;
    SharedPreferencesUtil preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_home_screen);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.navigation);
        this.preferences = new SharedPreferencesUtil(getApplicationContext());
        String highScore = preferences.getName();
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
                Log.d("Token",token.get(0).getKey()) ;
                Log.d("UserInformation",userInfo.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bottomNavigationSetUp(bottomNavigationView,this);
        navigationViewSetUp(navigationView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
