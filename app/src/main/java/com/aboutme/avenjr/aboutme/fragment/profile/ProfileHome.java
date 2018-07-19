package com.aboutme.avenjr.aboutme.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.aboutme.avenjr.aboutme.activity.ProfileSectionDescription;
import com.aboutme.avenjr.aboutme.data.ProfileInfo;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileHome extends Fragment {

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

    ArrayList<String> data;
    ProfileInfo profileInfo;
    SharedPreferencesUtil preferences;
    Boolean click = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_home, container, false);
        ButterKnife.bind(this,view);
        preferences = new SharedPreferencesUtil(Objects.requireNonNull(getActivity()).getApplicationContext());

        setSectionData();
        setProfileHeader();

        header.setView(getString(R.string.profile_header_string),this.getActivity(),false);
        selectProfileRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        ProfileAdapter adapter = new ProfileAdapter(data);
        selectProfileRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ProfileSectionDescription.class);
                intent.putExtra("header",data.get(position));
                startActivity(intent);
            }
        });
        return  view;
    }

    @OnClick(R.id.profile_header)
    public void headerClick(){
        if(click){
            click = false;
            header.animate().translationY(-header.getHeight());
        }else {
            click = true;
            header.animate().translationY(0);
        }
    }

    private void setProfileHeader() {
        ImageUtil.setImage(getContext(),preferences.getProfileImageUrl(),profileImage);
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

    private void setSectionData() {
        data = new ArrayList<>();
        profileInfo = new ProfileInfo();
        data.addAll(profileInfo.getAllUserProfileSections());
    }
}
