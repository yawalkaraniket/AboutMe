package com.aboutme.avenjr.aboutme.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.aboutme.avenjr.aboutme.R;

public class NavigationHeaderHome extends Fragment {

    ImageButton navigationHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.navigation_header, container, false);
    }

}
