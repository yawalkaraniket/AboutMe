package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.BlogFragment;
import com.aboutme.avenjr.aboutme.fragment.DocumentsFragment;
import com.aboutme.avenjr.aboutme.fragment.FeedbackFragment;
import com.aboutme.avenjr.aboutme.fragment.HomeFragment;
import com.aboutme.avenjr.aboutme.fragment.WorkFragment;
import com.aboutme.avenjr.aboutme.view.DialogUtil;

/**
 * Created by AvenjR on 11/5/18.
 */

public abstract class BaseActivity extends FragmentActivity {

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
                        item.setChecked(true);
                        replaceFragment(new BlogFragment());
                        closeDrawer(activity);
                        break;
                    case R.id.my_work:
                        item.setChecked(true);
                        closeDrawer(activity);
                        replaceFragment(new WorkFragment());
                        break;
                    case R.id.documents:
                        item.setChecked(true);
                        closeDrawer(activity);
                        replaceFragment(new DocumentsFragment());
                        break;
                    case R.id.app_share:
                        item.setChecked(true);
                        closeDrawer(activity);
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.app_rate:
                        item.setChecked(true);
                        closeDrawer(activity);
                        break;
                    case R.id.app_feedback:
                        item.setChecked(true);
                        closeDrawer(activity);
                        replaceFragment(new FeedbackFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void deselectAllMenus(NavigationView navigationView) {
        for (int index = 0; index < navigationView.getMenu().size(); index++) {
            for (int subIndex = index; subIndex < navigationView.getMenu().getItem(index).getSubMenu().size(); subIndex++) {
                navigationView.getMenu().getItem(index).getSubMenu().getItem(subIndex).setChecked(false);
            }
        }
        navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setChecked(false);
    }

    public void closeDrawer(Activity activity) {
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.START);
    }

    public void replaceFragment(Object object){
        Fragment fragment= (Fragment) object;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.blank_frgment,fragment);
        transaction.commit();
    }
    public void addFragment(Object object){
        Fragment fragment= (Fragment) object;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment,"gfd");
        transaction.commit();
    }

}
