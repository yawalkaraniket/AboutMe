package com.aboutme.avenjr.aboutme.Adapter.Profile;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.FireBaseUtil;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.activity.UserInformation;
import com.aboutme.avenjr.aboutme.data.ProfileInfo;
import com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

public class ProfileSectionAdapter extends RecyclerView.Adapter<ProfileSectionAdapter.ProfileSectionAdapterViewHolder> {

    private RecyclerViewListener mRecyclerViewListener;
    private ArrayList<String> section_name = new ArrayList<>();
    private ProfileInfo mProfileInfo = new ProfileInfo();
    private Boolean click = true;
    private DatabaseReference mDatabaseReference;
    private UserInformation userInfo;
    private SharedPreferencesUtil preferences;

    public ProfileSectionAdapter(ArrayList<String> data,SharedPreferencesUtil preferences) {
        this.section_name = data;
        this.preferences = preferences;
    }

    @NonNull
    @Override
    public ProfileSectionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profile_sections_adapter_layout, parent, false);
        this.mDatabaseReference = getFireBaseReference("UserInformation/"+preferences.getToken()+"/Profile");
        this.userInfo = new UserInformation();
        return new ProfileSectionAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProfileSectionAdapterViewHolder holder, int position) {
        holder.sectionName.setText(section_name.get(position));
        holder.sectionDescription.setText(holder.sectionDescription.getText() + " " +
                section_name.get(position).toLowerCase());
        holder.position = position;
    }


    @Override
    public int getItemCount() {
        return section_name.size();
    }

    class ProfileSectionAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView sectionName;
        TextView sectionDescription;
        int position = 0;

        ProfileSectionAdapterViewHolder(View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.profile_section_name);
            sectionDescription = itemView.findViewById(R.id.section_description);
            sectionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewListener.onItemClick(sectionName, position);
                    mProfileInfo.setAllUserProfileSections(section_name.get(position));
                    if (click) {
                        itemView.setAlpha(0.5f);
                        click = false;
                        saveSectionName(section_name.get(position));
                    } else {
                        itemView.setAlpha(1f);
                        click = true;
                        removeSectionName(section_name.get(position));
                    }
                }
            });
        }
    }

    private void saveSectionName(String section) {
        mDatabaseReference.child(section).setValue("");
    }

    private void removeSectionName(String section) {
        mDatabaseReference.child(section).removeValue();
    }

    public void setItemClickListener(RecyclerViewListener recyclerViewListener) {
        this.mRecyclerViewListener = recyclerViewListener;
    }
}
