package com.aboutme.avenjr.aboutme.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.data.Movie;
import com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

import static com.aboutme.avenjr.aboutme.Utils.ImageUtil.setImage;

/**
 * Created by tudip on 7/5/18.
 */

public class RecyclerViewAdapterExample extends RecyclerView.Adapter<RecyclerViewAdapterExample.RecyclerViewHolderExample> {

    private RecyclerViewListener recyclerViewListener;
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w342";
    private List<Movie> movies;
    Context mContext;

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private ArrayList<String> data;
    private ArrayList<String> imageData;


    public RecyclerViewAdapterExample(ArrayList<String> data, ArrayList<String> imagedata, Context context) {
        this.data = data;
        this.imageData = imagedata;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolderExample onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_recycler_view, parent, false);
        return new RecyclerViewHolderExample(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderExample holder, int position) {
        String title = data.get(position);
        holder.mTextView.setText(title);
        String image_url = IMAGE_URL_BASE_PATH + imageData.get(position);
        setImage(mContext, image_url, holder.mImageView);


        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolderExample extends ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        int position = 0;

        public RecyclerViewHolderExample(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.recycler_view_image);
            mTextView = itemView.findViewById(R.id.recycler_view_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewListener.onItemClick(itemView, position);
                }
            });
        }
    }

    public void setItemClickListener(RecyclerViewListener recyclerViewListener) {
        this.recyclerViewListener = recyclerViewListener;
    }
}
