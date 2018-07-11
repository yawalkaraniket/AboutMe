package com.aboutme.avenjr.aboutme.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.Adapter.ProfileAdapter;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {


    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.select_profile_recycler_view)
    RecyclerView selectProfileRecyclerView;

    @BindView(R.id.profile_parent_layout)
    RelativeLayout parentLayout;

    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        data.add("first");
        data.add("second");

        header.setUp(this);
        header.setView(getString(R.string.profile_header_string), this);
        selectProfileRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProfileAdapter adapter = new ProfileAdapter(data);
        selectProfileRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.profile_parent_layout)
    public void click() {
        //  do nothing
    }
}
