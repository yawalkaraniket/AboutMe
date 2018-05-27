package com.aboutme.avenjr.aboutme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutme.avenjr.aboutme.Adapter.RecyclerViewAdapterExample;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.work_navigation)
    NavigationHeader header;

    @BindView(R.id.home_page_recycler_view)
    RecyclerView mRecyclerView;

    String data[] = {"sfsd", "dfsf", "sefsdfs", "sfsfsdf", "sfsdfsd", "sdfsdfsd",
            "dfsfsf", "sdfsfdsd", "dsdfsdf", "dsf", "sdfkjsdd", "kjsdfkjshd"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        header.setUp(this.getActivity());
        header.setView("Home", this.getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(new RecyclerViewAdapterExample(data));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
