package com.aboutme.avenjr.aboutme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DocumentsFragment extends BaseFragment {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.documents_body)
    RelativeLayout body;

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

    @OnClick(R.id.documents_body)
    public void blockView(){
//          do nothing
    }
}
