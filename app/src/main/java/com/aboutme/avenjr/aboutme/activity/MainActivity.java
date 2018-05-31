package com.aboutme.avenjr.aboutme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aboutme.avenjr.aboutme.R;

public class MainActivity extends BaseActivity {

    private static boolean alreadyLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            alreadyLogin = true;
        } else if(alreadyLogin){
            Intent intent = new Intent(getBaseContext(),MpinActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button continueWithFacebook = findViewById(R.id.layout_continue_with_facebook);
        Button continueWithMail = findViewById(R.id.layout_continue_with_mail);
        Button haveAnAccount = findViewById(R.id.have_an_account_button);

        continueWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        continueWithMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SignInActivity.class);
                startActivity(intent);
            }
        });
        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        if(!isConnectedToInternet())
            netWorkErrorDialog();
        super.onResume();
    }
}
