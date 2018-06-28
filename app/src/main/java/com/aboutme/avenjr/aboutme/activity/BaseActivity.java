package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.BuildConfig;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.fragment.BlogFragment;
import com.aboutme.avenjr.aboutme.fragment.DocumentsFragment;
import com.aboutme.avenjr.aboutme.fragment.HomeFragment;
import com.aboutme.avenjr.aboutme.fragment.WorkFragment;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;

/**
 * Created by AvenjR on 11/5/18.
 */

public abstract class BaseActivity extends FragmentActivity {

    private long mLastClickTime = 0;
    Toast toast;
    public final static int PROGRESS_TYPE_NO_BLOCK = 0;
    public final static int PROGRESS_TYPE_BLOCK_TRANSPARENT = 1;
    public final static int PROGRESS_TYPE_BLOCK_BLACK_TRANSPARENT = 2;

    protected View progressBar;
    protected View blockLayout;
    protected FontEditText loading;

    public void setupProgress(RelativeLayout layout) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        progressBar = new ProgressBar(layout.getContext());
        progressBar.setLayoutParams(new LinearLayout.LayoutParams((int) (60 * dp()), (int) (60 * dp())));
        RelativeLayout.LayoutParams params;
        linearLayout.setLayoutParams(params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        loading = new FontEditText(this);
        loading.setText(getString(R.string.loading_generic_message));
        loading.setTextColor(getResources().getColor(R.color.white));
        loading.setGravity(Gravity.CENTER);
        linearLayout.addView(progressBar);
        linearLayout.addView(loading);
        blockLayout = new RelativeLayout(this);
        blockLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(blockLayout);
        blockLayout.setBackgroundColor(getResources().getColor(R.color.black_transparent));
        blockLayout.setOnClickListener(click -> {});
        layout.addView(linearLayout);
        blockLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    public void showProgress() {
        showProgress(PROGRESS_TYPE_BLOCK_TRANSPARENT);
    }

    public void showProgress(int type) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (type == PROGRESS_TYPE_BLOCK_BLACK_TRANSPARENT && blockLayout != null) {
            blockLayout.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
            blockLayout.setBackgroundColor(getResources().getColor(R.color.black_transparent));
        }
        if (type == PROGRESS_TYPE_BLOCK_TRANSPARENT && blockLayout != null) {
            blockLayout.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            blockLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        if (type == PROGRESS_TYPE_NO_BLOCK && blockLayout != null) {
            blockLayout.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        }
    }

    public void showProgress(boolean blockScreen) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (blockScreen && blockLayout != null) {
            blockLayout.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        if (blockLayout != null) {
            blockLayout.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        }
    }

    public void displayToast(Context context, CharSequence message){
        toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideProgress(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void bottomNavigationSetUp(BottomNavigationView navigationView, Activity activity) {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selectedMenuId = item.getItemId();
                switch (selectedMenuId) {
                    case R.id.home:
                        item.setChecked(true);
                        replaceFragment(new HomeFragment());
                        closeDrawer(activity);
                        break;
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
                }
                return false;
            }
        });
    }

    public void navigationViewSetUp(NavigationView navigationView, Activity activity) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                deselectAllMenus(navigationView);
                int selectedMenuId = item.getItemId();
                switch (selectedMenuId) {
                    case R.id.home:
                        item.setChecked(true);
                        replaceFragment(new HomeFragment());
                        closeDrawer(activity);
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
                        sendMail();
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
        transaction.replace(R.id.blank_fragment,fragment);
        transaction.commit();
    }
    public void addFragment(Object object){
        Fragment fragment= (Fragment) object;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment,"gfd");
        transaction.commit();
    }

    protected boolean restrictDoubleTap() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return true;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        return false;
    }

    public static float dp() {
        DisplayMetrics metrics = getDisplayMetrics();
        return (float) metrics.densityDpi / 160f;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public void sendMail(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "support@aboutme.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.aboutme_app_support));
        emailIntent.putExtra(Intent.EXTRA_TEXT, generateText());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private String generateText() {
        String str = getString(R.string.hi_aboutme)
                .replace("@version", BuildConfig.VERSION_NAME+"")
                .replace("@build",BuildConfig.VERSION_CODE+"")
                .replace("@device_type",android.os.Build.MODEL+"")
                .replace("@os_version", Build.VERSION.RELEASE+"")
                .replace("@email","yawalkaraniket93@gmail.com");
        return str;
    }
}
