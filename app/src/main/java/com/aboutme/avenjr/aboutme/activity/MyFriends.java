package com.aboutme.avenjr.aboutme.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.profile.myAddedFriendsFragment;
import com.aboutme.avenjr.aboutme.fragment.profile.myFriendsFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

public class MyFriends extends BaseActivity implements myFriendsFragment.selectedListItem {

    @BindView(R.id.friend_details)
    TextView friendDetails;

    String selectedItem;
    HashMap<String, String> userInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        ButterKnife.bind(this);


        if(getIntent().getStringExtra("fragment").equals("myAddedFriendsFragment")){
            android.support.v4.app.FragmentManager manager =  getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.other_fragment);
            fragment = new myAddedFriendsFragment();
            manager.beginTransaction()
                    .add(R.id.other_fragment,fragment)
                    .commit();

        }else {
            //  the phone is in portrait mode
            if (findViewById(R.id.layout_portrait) != null) {
                android.support.v4.app.FragmentManager manager = this.getSupportFragmentManager();
                manager.beginTransaction()
                        .hide(manager.findFragmentById(R.id.friends_details_fragment))
                        .show(manager.findFragmentById(R.id.friends_fragment))
                        .commit();
            }

            //  the phone is in land scape mode
            if (findViewById(R.id.layout_land) != null) {
                android.support.v4.app.FragmentManager manager = this.getSupportFragmentManager();
                manager.beginTransaction()
                        .show(manager.findFragmentById(R.id.friends_details_fragment))
                        .show(manager.findFragmentById(R.id.friends_fragment))
                        .commit();
            }
        }
    }

    @Override
    public void selectedListItemPosition(int position,String selectedItem) {
        this.selectedItem = selectedItem;
        friendDetails.setText(selectedItem);

        getFireBaseReference("UserInformation").orderByChild("name").equalTo(selectedItem).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String token = userSnapshot.getKey();
                        for (DataSnapshot programSnapshot : userSnapshot.getChildren()) {
                            userInfo.put(programSnapshot.getKey(), Objects.requireNonNull(programSnapshot.getValue()).toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //  the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            android.support.v4.app.FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.friends_details_fragment))
                    .hide(manager.findFragmentById(R.id.friends_fragment))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @OnClick(R.id.place_call)
    public void call() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7875430906"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    @OnClick(R.id.find_location)
    public void searchFriend(){
        Uri mapUri = Uri.parse("geo:0,0?q=Datt mandir road wakad");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
