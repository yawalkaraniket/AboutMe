package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.aboutme.avenjr.aboutme.main.view.DialogUtil;

/**
 * Created by tudip on 11/5/18.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
    public void netWorkErrorDialog(Boolean back){
        DialogUtil.yesDialog(this,"Network Error","please check your network....", click->{
            onBackPressed();
        });
    }

    public void netWorkErrorDialog(){
        DialogUtil.yesDialog(this,"Network Error","please check your network....", click->{
            onBackPressed();
        });
    }

    public void showProgress(ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgress(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }
}
