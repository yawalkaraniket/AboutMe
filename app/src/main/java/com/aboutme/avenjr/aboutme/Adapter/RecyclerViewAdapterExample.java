package com.aboutme.avenjr.aboutme.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

/**
 * Created by tudip on 7/5/18.
 */

public class RecyclerViewAdapterExample extends RecyclerView.Adapter<RecyclerViewAdapterExample.RecyclerViewHolderExample> {

    private String[] data;


    public RecyclerViewAdapterExample(String data[]) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewHolderExample onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_recycler_view,parent,false);
        return new RecyclerViewHolderExample(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderExample holder, int position) {
        String title = data[position];
        holder.mTextView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RecyclerViewHolderExample extends ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        public RecyclerViewHolderExample(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.recycler_view_image);
            mTextView = itemView.findViewById(R.id.recycler_view_text);

        }
    }
}
