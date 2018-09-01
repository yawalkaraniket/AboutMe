package com.aboutme.avenjr.aboutme.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.profile.myFriendsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFriends extends BaseActivity implements myFriendsFragment.selectedListItem {

    @BindView(R.id.friend_details)
    TextView friendDetails;

    ArrayList<String> description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        ButterKnife.bind(this);

        description = new ArrayList<>();
        description.add("first description");
        description.add("second description");
        description.add("third description");

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

    @Override
    public void selectedListItemPosition(int position) {
        friendDetails.setText(description.get(position));

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
