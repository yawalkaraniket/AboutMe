package com.aboutme.avenjr.aboutme.fragment.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

    ArrayList<String> userCompleteInfo;
    selectedListItem activity;

    public myFriendsFragment() {
        // Required empty public constructor
    }

    public interface selectedListItem
    {
        void selectedListItemPosition(int position,String selectedItem);
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



        DatabaseReference mDatabaseReference = getFireBaseReference("UserInformation");
        ArrayList<ArrayList<String>> listInfo = new ArrayList<>();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userCompleteInfo = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot s: snapshot.getChildren() ){
                        if(s.getKey().equals("name"))
                        userCompleteInfo.add(s.getValue().toString());
                    }
                    listInfo.add(userCompleteInfo);
                }
                setListAdapter(new ArrayAdapter<>(Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_expandable_list_item_1,listInfo.get(0)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
            activity.selectedListItemPosition(position,userCompleteInfo.get(position));
    }
}
