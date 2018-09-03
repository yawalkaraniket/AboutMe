package com.aboutme.avenjr.aboutme.Adapter.Profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.AppTextView;

import java.util.ArrayList;

public class FriendsListAdapter extends ArrayAdapter<String> {

    ArrayList<String> friendsList = new ArrayList<>();
    Context context;

    public FriendsListAdapter(@NonNull Context context, ArrayList<String> list) {
        super(context, R.layout.my_friends_list_layout,list);
        this.friendsList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.my_friends_list_layout,parent,false);

        AppTextView textView = rowView.findViewById(R.id.profile_name);
        textView.setText(friendsList.get(position));

        return rowView;
    }
}
