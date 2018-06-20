package com.aboutme.avenjr.aboutme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DocumentsFragment extends BaseFragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_documents, container, false);
          ButterKnife.bind(this,view);
        header.setUp(this.getActivity());
        header.setView("Documents",this.getActivity());
        return view;
    }
}
