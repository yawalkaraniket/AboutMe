package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.DialogUtil;

/**
 * Created by AvenjR on 11/5/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public void netWorkErrorDialog(Boolean back) {
        DialogUtil.yesDialog(this, "Network Error", "please check your network....", click -> {
            onBackPressed();
        });
    }

    public void netWorkErrorDialog() {
        DialogUtil.yesDialog(this, "Network Error", "please check your network....", click -> {
            onBackPressed();
        });
    }

    public void showProgress(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }

    public void navigationViewSetUp(NavigationView navigationView, Activity activity) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                deselectAllMenus(navigationView);
                int selectedMenuId = item.getItemId();
                switch (selectedMenuId) {
                    case R.id.my_blog:
                        Toast.makeText(activity, "My Blog", Toast.LENGTH_SHORT).
                                show();
                        item.setChecked(true);
                        break;
                    case R.id.my_work:
                        Toast.makeText(activity, "My Work", Toast.LENGTH_SHORT).
                                show();
                        item.setChecked(true);
                        break;
                    case R.id.documents:
                        Toast.makeText(activity, "My Documents", Toast.LENGTH_SHORT).
                                show();
                        item.setChecked(true);
                        break;
                    case R.id.app_share:
                        Toast.makeText(activity, "My AppShare", Toast.LENGTH_SHORT).
                                show();
                        item.setChecked(true);
                        break;
                    case R.id.app_rate:
                        Toast.makeText(activity, "My AppRate", Toast.LENGTH_SHORT).
                                show();
                        item.setChecked(true);
                        break;
                    case R.id.app_feedback:
                        Toast.makeText(activity, "My AppFeedback", Toast.LENGTH_SHORT).
                                show();
                        item.setChecked(true);
                        break;
                }
                return true;
            }
        });
    }

    private void deselectAllMenus(NavigationView navigationView) {
        for (int index = 0; index < navigationView.getMenu().size(); index++) {
            navigationView.getMenu().getItem(index).setChecked(false);
        }
    }
}
