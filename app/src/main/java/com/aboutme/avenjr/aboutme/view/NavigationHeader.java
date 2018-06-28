package com.aboutme.avenjr.aboutme.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.activity.MainActivity;

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
    View separator;

    Context mContext;


    public NavigationHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_header, null);
        ButterKnife.bind(this, view);
        this.mContext = context;
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

        headerRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(headerRight, activity);
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
        headerRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(headerRight, activity);
            }
        });
    }

    public void setView(final String screen, Activity activity) {
        headerText.setText(screen.toUpperCase());
        if (screen.equals("Login") || screen.equals("SignUp")) {
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_arrow_back));
            headerRight.setVisibility(GONE);
        } else if (screen.equals("mPin")) {
            headerRight.setImageDrawable(getResources().getDrawable(R.drawable.baseline_more_vert_black_18));
            separator.setVisibility(GONE);
            headerText.setVisibility(GONE);
            navigationHome.setImageDrawable(getResources().getDrawable(R.drawable.baseline_chevron_left_black_18));
            navigationHome.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void openDrawer(Activity activity) {
        DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.START);
    }

    public void showPopup(View v, Activity activity) {
        PopupMenu popup = new PopupMenu(activity, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.top_left_pop_up_menu, popup.getMenu());
        popup.show();
    }

}
