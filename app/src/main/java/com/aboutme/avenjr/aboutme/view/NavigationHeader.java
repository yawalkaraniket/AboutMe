package com.aboutme.avenjr.aboutme.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.activity.MainActivity;
import com.aboutme.avenjr.aboutme.activity.ProfileActivity;
import com.aboutme.avenjr.aboutme.fragment.profile.ProfileSections;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aboutme.avenjr.aboutme.activity.MainActivity.mGoogleApiClient;

/**
 * Created by AvenjR on 24/4/18.
 */

public class NavigationHeader extends RelativeLayout {

    @BindView(R.id.navigation_header_home)
    ImageView navigationHome;

    @BindView(R.id.navigation_header_text)
    TextView headerText;

    @BindView(R.id.navigation_header_right)
    ImageView headerRight;

    @BindView(R.id.navigation_header_separator)
    View separator;

    @BindView(R.id.navigation_header)
    RelativeLayout parentLayout;

    Context context;
    Activity activity;
    SharedPreferencesUtil preferences;


    public NavigationHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_header, null);
        ButterKnife.bind(this, view);
        this.context = context;
        addView(view);
    }

    public NavigationHeader(Context context) {
        super(context);
    }

    public void setUp(final Activity activity, final String screen) {
        this.activity = activity;
        navigationHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.equals("HomeScreen")) {
                    openDrawer();
                } else {
                    activity.onBackPressed();
                }
            }
        });

        headerRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(headerRight);
            }
        });
    }

    public void setUp(Activity activity) {
        this.activity = activity;
        this.preferences = new SharedPreferencesUtil(activity.getApplicationContext());
        navigationHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
        headerRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(headerRight);
            }
        });
    }

    public void setView(final String screen, Activity activity,Boolean forBackHeader) {
        this.activity = activity;
        headerText.setText(screen);
        if (forBackHeader) {
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_back));
            headerRight.setVisibility(GONE);
            navigationHome.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        } else if (screen.equals("mPin")) {
            headerRight.setImageDrawable(getResources().getDrawable(R.drawable.baseline_more_vert_black_18));
            separator.setVisibility(GONE);
            headerText.setVisibility(GONE);
            parentLayout.setBackground(getResources().getDrawable(R.color.white));
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.baseline_chevron_left_black_18));
            navigationHome.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finishAffinity();
                }
            });
            headerRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMpinPopup(headerRight);
                }
            });
        } else if (screen.equals(activity.getString(R.string.profile_header_string))) {
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_back));
            headerRight.setImageDrawable(getResources().getDrawable(R.drawable.add_circle_button));
            navigationHome.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
            headerRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new ProfileSections());
                }
            });
        }
    }

    public void showMpinPopup(View v) {
        PopupMenu popup = new PopupMenu(activity, v);
        setUpMenu(popup);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.mpin_popup_menu, popup.getMenu());
        popup.show();
    }

    public void replaceFragment(Object object) {
        Fragment fragment = (Fragment) object;
        FragmentActivity activity = (FragmentActivity) this.activity;
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.profile_blank_fragment, fragment);
        transaction.addToBackStack(object.getClass().getSimpleName());
        transaction.commit();
    }

    public void openDrawer() {
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.START);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(activity, v);
        setUpMenu(popup);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.top_left_pop_up_menu, popup.getMenu());
        popup.show();
    }

    public void setUpMenu(PopupMenu upMenu) {
        upMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.view_profile:
                        Intent intent = new Intent(activity, ProfileActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.logout:
                        DialogUtil.yesDialog(activity, "Success", "you are successfully logout from the application!...", click -> {
                            mGoogleApiClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(activity, MainActivity.class);
                                    context.startActivity(intent);
                                    activity.finish();
                                    preferences.clearPreferences();
                                }
                            });
                        });
                        break;
                    case R.id.reset_mpin:
                        DialogUtil.yesDialog(activity, "Success!", "your mPin has been send to your registred email account...",
                                click -> {

                                });
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
}
