package com.aboutme.avenjr.aboutme.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AvenjR on 24/4/18.
 */

public class NavigationHeader extends RelativeLayout {


    @BindView(R.id.navigation_header_home)
    ImageView navigationHome;

    @BindView(R.id.navigation_header_text)
    TextView headerText;

    @BindView(R.id.navigation_header_right)
    ImageButton headerRight;

    @BindView(R.id.navigation_header_separator)
    View seperator;


    public NavigationHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_header, null);
        ButterKnife.bind(this, view);
        addView(view);
    }

    public void setUp(final Activity activity, final String screen) {
        navigationHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screen.equals("HomeScreen")) {
                    openDrawer(activity);
                } else {
                    activity.onBackPressed();
                }
            }
        });
    }

    public void setUp(Activity activity) {
        navigationHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(activity);
            }
        });
    }

    public void setView(final String screen,Activity activity) {
        headerText.setText(screen.toUpperCase());
        if (screen.equals("Login") || screen.equals("SignUp")) {
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_arrow_back));
            headerRight.setVisibility(GONE);
        } else if (screen.equals("mPin")){
            headerRight.setImageDrawable(getResources().getDrawable(R.drawable.baseline_more_vert_black_18));
            seperator.setVisibility(GONE);
            headerText.setVisibility(GONE);
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.baseline_chevron_left_black_18));
            navigationHome.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
         }
    }

    public void openDrawer(Activity activity) {
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.START);
    }
}
