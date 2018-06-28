package com.aboutme.avenjr.aboutme.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.Adapter.RecyclerViewAdapterExample;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.data.Movie;
import com.aboutme.avenjr.aboutme.data.MovieResponse;
import com.aboutme.avenjr.aboutme.data.apiUtil;
import com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.firebase.ui.auth.ui.phone.SubmitConfirmationCodeFragment.TAG;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.work_navigation)
    NavigationHeader header;

    @BindView(R.id.home_page_recycler_view)
    RecyclerView mRecyclerView;

    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> Imagedata = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        header.setUp(this.getActivity());
        setupProgress((RelativeLayout) view);
        header.setView("Home", this.getActivity());
        showProgress();
        connectAndGetApiData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        IntentFilter filter = new IntentFilter();
        filter.addAction("swipe");
        getActivity().registerReceiver(mBroadcastReceiver, filter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                getContext().sendBroadcast(new Intent("swipe"));
            }
        });
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

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals("swipe")) {
//                    Do on swipe of recycler view.
                }
            }
        }
    };

    public void connectAndGetApiData() {
        apiUtil.getBaseUri().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                for (int i = 0; i < movies.size(); i++) {
                    data.add(movies.get(i).getOriginalTitle());
                    Imagedata.add(movies.get(i).getPosterPath());
                }
                RecyclerViewAdapterExample adapter = new RecyclerViewAdapterExample(data, Imagedata, getContext());
                mRecyclerView.setAdapter(adapter);
                hideProgress();
                adapter.setItemClickListener(new RecyclerViewListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        displayToast(getContext(), data.get(position));
                    }
                });
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e("Response fail", throwable.toString());
            }
        });
    }
}
