package com.aboutme.avenjr.aboutme.fragment.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aboutme.avenjr.aboutme.Adapter.Profile.FriendsListAdapter;
import com.aboutme.avenjr.aboutme.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class myAddedFriendsFragment extends Fragment {


    @BindView(R.id.addedFriendsList)
    ListView addedFriendsList;

    public myAddedFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_added_friends, container, false);
        ButterKnife.bind(this,view);

        ArrayList<String> list = new ArrayList<>();
        list.add("first");
        list.add("Second");
        addedFriendsList.setAdapter(new FriendsListAdapter(getActivity().getApplicationContext(),list));

        return view;
    }

}
