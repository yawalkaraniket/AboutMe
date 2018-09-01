package com.aboutme.avenjr.aboutme.fragment.profile;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

/**
 * @Purpose To retrieve the list of user currently register to the application.
 * @Organization AvenjR.Pvt.Ltd
 * @Author Aniket Yawalkar
 * @Date 08/22/2018
 */

public class myFriendsFragment extends ListFragment {


    public myFriendsFragment() {
        // Required empty public constructor
    }

    selectedListItem activity;

    public interface selectedListItem
    {
        void selectedListItemPosition(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (selectedListItem)context;
    }
    enum user{
        token,
        info
    }
    HashMap<user, String> userInfo = new HashMap<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFireBaseReference("UserInformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        userInfo.put(user.token,userSnapshot.getKey());
                        for (DataSnapshot programSnapshot : userSnapshot.getChildren()) {
                            userInfo.put(user.info, programSnapshot.getValue().toString());
                        }
                    }

                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ArrayList<String> friends = new ArrayList<>();
        friends.add("aniket");
        friends.add("shubham");
        friends.add("yash");

        setListAdapter(new ArrayAdapter<>(Objects.requireNonNull(getActivity()),android.R.layout.simple_expandable_list_item_1,friends));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
            activity.selectedListItemPosition(position);
    }
}
