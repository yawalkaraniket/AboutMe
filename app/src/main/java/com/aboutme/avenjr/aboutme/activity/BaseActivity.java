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
import android.widget.TextView;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.BuildConfig;
import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.ImageUtil;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.fragment.BlogFragment;
import com.aboutme.avenjr.aboutme.fragment.DocumentsFragment;
import com.aboutme.avenjr.aboutme.fragment.EventFragment;
import com.aboutme.avenjr.aboutme.fragment.HomeFragment;
import com.aboutme.avenjr.aboutme.fragment.WorkFragment;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

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
    Activity activity;
    private SharedPreferencesUtil preferences;
    BottomNavigationView bottomNavigationView;
    DatabaseReference databaseReference;

    public void setupProgress(RelativeLayout layout) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        progressBar = new ProgressBar(layout.getContext());
        progressBar.setLayoutParams(new LinearLayout.LayoutParams((int) (60 * dp()),
                (int) (60 * dp())));
        RelativeLayout.LayoutParams params;
        linearLayout.setLayoutParams(params = new RelativeLayout.LayoutParams(ViewGroup.
                LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        loading = new FontEditText(this);
        loading.setText(getString(R.string.loading_generic_message));
        loading.setTextColor(getResources().getColor(R.color.white));
        loading.setGravity(Gravity.CENTER);
        linearLayout.addView(progressBar);
        linearLayout.addView(loading);
        blockLayout = new RelativeLayout(this);
        blockLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(blockLayout);
        blockLayout.setBackgroundColor(getResources().getColor(R.color.black_transparent));
        blockLayout.setOnClickListener(click -> {
        });
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

    public void displayToast(Context context, CharSequence message) {
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public void netWorkErrorDialog(Boolean back) {
        DialogUtil.yesDialog(this, "Network Error",
                "please check your network....", click -> {
            onBackPressed();
        });
    }

    public void verifyNetwork() {
        if (!isConnectedToInternet()) {
            netWorkErrorDialog();
        }
    }

    public void netWorkErrorDialog() {
        DialogUtil.noNetworkDialog(this);
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
        this.bottomNavigationView = navigationView;
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
                    case R.id.events:
                        item.setChecked(true);
                        closeDrawer(activity);
                        replaceFragment(new EventFragment());
                        break;
                }
                return false;
            }
        });
    }

    public void navigationViewSetUp(NavigationView navigationView, Activity activity) {
        preferences = new SharedPreferencesUtil(activity.getApplicationContext());
        databaseReference = getFireBaseReference("UserInformation/"+preferences.getToken()+"/rate");
        if (preferences.getLoginWith().equals("google") || preferences.getLoginWith().equals("email")
                || preferences.getLoginWith().equals("SignUp")) {

            CircleImageView profileImge = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
            TextView name = navigationView.getHeaderView(0).findViewById(R.id.profile_name);
            TextView email = navigationView.getHeaderView(0).findViewById(R.id.profile_email);
            ImageUtil.setImage(activity, preferences.getProfileImageUrl(), profileImge);
            name.setText(preferences.getName());
            email.setText(preferences.getEmail());

        }

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
                        shareApplication();
                        break;
                    case R.id.app_rate:
                        item.setChecked(true);
                        closeDrawer(activity);
                        DialogUtil.rateMe(activity,"Rate Me",click->{
                            databaseReference.setValue(preferences.getAppRating());
                        });
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
            for (int subIndex = index; subIndex < navigationView.getMenu().getItem(index).
                    getSubMenu().size(); subIndex++) {
                navigationView.getMenu().getItem(index).getSubMenu().getItem(subIndex).
                        setChecked(false);
            }
        }
        navigationView.getMenu().getItem(1).getSubMenu().getItem(0).setChecked(false);
    }

    public void closeDrawer(Activity activity) {
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.START);
    }

    public void replaceFragment(Object object) {
        Fragment fragment = (Fragment) object;
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getFragments().get(manager.getFragments().size()-1).getClass().
                equals(object.getClass())){
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.blank_fragment, fragment);
        transaction.addToBackStack(object.getClass().getSimpleName());
        transaction.commit();
    }

    public void addFragment(Object object) {
        Fragment fragment = (Fragment) object;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment, "gfd");
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

    public void sendMail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "support@aboutme.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.aboutme_app_support));
        emailIntent.putExtra(Intent.EXTRA_TEXT, generateText());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private String generateText() {
        String str = getString(R.string.hi_aboutme)
                .replace("@version", BuildConfig.VERSION_NAME + "")
                .replace("@build", BuildConfig.VERSION_CODE + "")
                .replace("@device_type", android.os.Build.MODEL + "")
                .replace("@os_version", Build.VERSION.RELEASE + "")
                .replace("@email", "yawalkaraniket93@gmail.com");
        return str;
    }

    private void shareApplication() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBodyText = "Now, connect your profile to the world \n and " +
                "\n help to manage your activities.\n Click on the below link";
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "About Me");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(shareIntent, "Shearing Option"));
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
