package com.aboutme.avenjr.aboutme.fragment.profile;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.Adapter.ProfileAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.ImageUtil;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.activity.MyFriends;
import com.aboutme.avenjr.aboutme.activity.ProfileSectionDescription;
import com.aboutme.avenjr.aboutme.activity.RssFeeds;
import com.aboutme.avenjr.aboutme.activity.SqlOperations;
import com.aboutme.avenjr.aboutme.activity.ViewBindingExample;
import com.aboutme.avenjr.aboutme.data.ProfileInfo;
import com.aboutme.avenjr.aboutme.fragment.BaseFragment;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

public class ProfileHome extends BaseFragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.select_profile_recycler_view)
    RecyclerView selectProfileRecyclerView;

    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @BindView(R.id.profile_name)
    TextView profileName;

    @BindView(R.id.profile_email)
    TextView profileEmail;

    @BindView(R.id.profile_header)
    RelativeLayout profileHeader;

    @BindView(R.id.profile_info_layout)
    RelativeLayout profileInfoParent;

    @BindView(R.id.my_friends)
    RelativeLayout friends;

    @BindView(R.id.my_added_friends)
    RelativeLayout myFriends;

    public static ArrayList<String> profileSections = new ArrayList<>();
    ProfileInfo profileInfo;
    SharedPreferencesUtil preferences;
    Boolean click = true;
    DatabaseReference mDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_home, container, false);
        ButterKnife.bind(this, view);
        preferences = new SharedPreferencesUtil(Objects.requireNonNull(getActivity()).getApplicationContext());

        setProfileHeader();
        mDatabaseReference = getFireBaseReference("UserInformation/" + preferences.getToken() + "/Profile");
        setProfileData();
        header.setView(getString(R.string.profile_header_string), this.getActivity(), false);
        selectProfileRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }

    private void setProfileData() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileSections.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    profileSections.add(snapshot.getKey());
                }
                ProfileAdapter adapter = new ProfileAdapter(profileSections);
                selectProfileRecyclerView.setAdapter(adapter);
                selectProfileRecyclerView.setItemAnimator(new DefaultItemAnimator());
                adapter.setItemClickListener(new com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), ProfileSectionDescription.class);
                        intent.putExtra("header", profileSections.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.my_friends)
    public void showFriends() {
        Intent intent = new Intent(getActivity(), MyFriends.class);
        intent.putExtra("fragment","myFriendsFragment");
        startActivity(intent);
    }

    @OnClick(R.id.my_added_friends)
    public void addedFriends(){
        Intent intent = new Intent(getActivity(), MyFriends.class);
        intent.putExtra("fragment","myAddedFriendsFragment");
        startActivity(intent);
    }

    @OnClick(R.id.rss_feeds)
    public void rssFeeds(){
        Intent intent = new Intent(getActivity(), RssFeeds.class);
        startActivity(intent);
    }

    @OnClick(R.id.profile_header)
    public void headerClick() {
        if (click) {
            click = false;
            header.animate().translationY(-header.getHeight());
            startProfileImageAnimation(profileImage);
            startButtonAnimation(profileInfoParent);
        } else {
            click = true;
            stopAnimation(profileImage);
            stopAnimation(profileInfoParent);
            header.animate().translationY(0);
        }
    }

    @OnClick(R.id.sql_updates)
    public void sqlOperations() {
        Intent intent = new Intent(getActivity(), SqlOperations.class);
        getActivity().startActivity(intent);
    }


    private void setProfileHeader() {
        ImageUtil.setImage(getContext(), preferences.getProfileImageUrl(), profileImage);
        profileEmail.setText(preferences.getEmail());
        profileName.setText(preferences.getName());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void startProfileImageAnimation(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY", -120f);
        animation.setDuration(500);
        animation.start();
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(view, "translationX", 220f);
        animation2.setDuration(500);
        animation2.start();
    }

    public void startButtonAnimation(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY", 80f);
        animation.setDuration(500);
        animation.start();
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(view, "translationX", -120f);
        animation2.setDuration(500);
        animation2.start();
    }

    public void stopAnimation(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY", 0f);
        animation.setDuration(500);
        animation.start();
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(view, "translationX", 0f);
        animation2.setDuration(500);
        animation2.start();
    }
}

