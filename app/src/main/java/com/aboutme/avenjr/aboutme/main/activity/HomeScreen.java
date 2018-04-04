package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

class HomeScreen extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FrameLayout homeScreenLayout;
    private ImageView navigationHeaderHome;
    private TextView profile_name;
    private TextView progile_email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        homeScreenLayout = findViewById(R.id.content_frame);
        navigationHeaderHome = findViewById(R.id.navigation_header_home);
        profile_name = findViewById(R.id.profile_name);
        progile_email = findViewById(R.id.profile_email);

//        Typeface customFount = Typeface.createFromAsset(getAssets(),"fonts/Futura-Bold.otf");
//        profile_name.setTypeface(customFount);
//        progile_email.setTypeface(customFount);
/*
       Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.home);
        Bitmap circularBitmap = ImageUtil.roundImage(bitmap);
        ImageView circularImageView = findViewById(R.id.ImageView);
        circularImageView.setImageBitmap(circularBitmap);
*/

        // Adding a navigation layout
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View navigaionHeader = layoutInflater.inflate(R.layout.navigation_header, homeScreenLayout,true);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });

/*
        navigationHeaderHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
*/
    }
}
