package com.aboutme.avenjr.aboutme.Adapter.Profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

import java.util.ArrayList;

public class ProfileSectionAdapter extends RecyclerView.Adapter<ProfileSectionAdapter.ProfileSectionAdapterViewHolder> {

    private ArrayList<String> section_name = new ArrayList<>();

    public ProfileSectionAdapter(ArrayList<String> data) {
        this.section_name = data;
    }

    @NonNull
    @Override
    public ProfileSectionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profile_sections_adapter_layout, parent, false);
        return new ProfileSectionAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileSectionAdapterViewHolder holder, int position) {
        holder.sectionName.setText(section_name.get(position));
        holder.position = position;
    }


    @Override
    public int getItemCount() {
        return section_name.size();
    }

    class ProfileSectionAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView sectionName;
        int position = 0;

        ProfileSectionAdapterViewHolder(View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.profile_section_name);
        }
    }
}
