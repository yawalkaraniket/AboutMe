package com.aboutme.avenjr.aboutme.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.Adapter.RecyclerViewAdapterExample;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.data.Movie;
import com.aboutme.avenjr.aboutme.data.MovieResponse;
import com.aboutme.avenjr.aboutme.data.ZomatoApiResponse;
import com.aboutme.avenjr.aboutme.data.apiUtil;
import com.aboutme.avenjr.aboutme.interfaces.RecyclerViewListener;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.work_navigation)
    NavigationHeader header;

    @BindView(R.id.home_page_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.actext_search_parent)
    RelativeLayout acTextViewParent;

    @BindView(R.id.remove_button)
    ImageView acViewRemoveButton;

    @BindView(R.id.actext_search)
    AutoCompleteTextView acTestView;

    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> imageData = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        header.setUp(this.getActivity());
        setupProgress((RelativeLayout) view);
        header.setView(getString(R.string.home_header_string), this.getActivity(), false);
        showProgress();
        connectAndGetApiData();

        String[] screenNames = {"Profile", "Blog", "Documents", "Profile Selection"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getActivity(),
                R.layout.actextview_list_view, screenNames);

        acTestView.setThreshold(2);
        acTestView.setAdapter(arrayAdapter);
        acTestView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUtil.errorDialog(getActivity(),"Position"+parent.getAdapter().getItem(position));
            }
        });

        //      Zomato API implementation but its not working
        apiUtil.getBaseUriForZomato().enqueue(new Callback<ZomatoApiResponse>() {
            @Override
            public void onResponse(Call<ZomatoApiResponse> call, Response<ZomatoApiResponse> response) {

                RecyclerViewAdapterExample adapter = new RecyclerViewAdapterExample(data, imageData);
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
            public void onFailure(Call<ZomatoApiResponse> call, Throwable throwable) {
                Log.e("Response fail", throwable.toString());
            }
        });

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
                    imageData.add(movies.get(i).getPosterPath());
                }
                RecyclerViewAdapterExample adapter = new RecyclerViewAdapterExample(data, imageData);
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

    @OnClick(R.id.remove_button)
    public void setAcViewRemoveButton() {
        acTextViewParent.animate().translationY(-(header.getHeight() + header.getHeight()));
        acTestView.setText("");
    }

    @OnClick(R.id.search_screen_button)
    public void acTextSearch() {
        acTextViewParent.setVisibility(View.VISIBLE);
        acTextViewParent.animate().translationY(0);
    }

    @Override
    public void onDestroy() {
        Objects.requireNonNull(getActivity()).unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }
}
