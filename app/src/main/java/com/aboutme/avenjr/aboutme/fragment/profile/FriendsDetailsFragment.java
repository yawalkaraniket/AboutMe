package com.aboutme.avenjr.aboutme.fragment.profile;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

import java.net.URI;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendsDetailsFragment extends Fragment {

    @BindView(R.id.friend_details)
    TextView details;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends_details, container, false);
        ButterKnife.bind(view);
        return view;
    }

    @OnClick(R.id.place_call)
    public void callFriend(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7875430906"));
        startActivity(intent);
    }

    @OnClick(R.id.find_location)
    public void searchFriend(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("Tudip Technology, Datt mandir road wakad"));
        startActivity(intent);
    }
}
