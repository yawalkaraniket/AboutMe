package com.aboutme.avenjr.aboutme.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.data.ProfileInfo;
import com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileAdapterViewHolder> {

    private RecyclerViewListener mRecyclerViewListener;
    private ArrayList<String> data = new ArrayList<>();

    public ProfileAdapter(ArrayList data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ProfileAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profile_adapter_layout, parent, false);
        return new ProfileAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapterViewHolder holder, int position) {
        holder.text.setText(data.get(position));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ProfileAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView profileSelectionButton;
        int position = 0;

        ProfileAdapterViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.select_profile_text);
            profileSelectionButton = view.findViewById(R.id.select_profile_button);
            profileSelectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewListener.onItemClick(profileSelectionButton, position);
                }
            });
        }
    }

    public void setItemClickListener(RecyclerViewListener recyclerViewListener) {
        this.mRecyclerViewListener = recyclerViewListener;
    }
}
