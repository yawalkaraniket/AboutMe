package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;
import com.google.firebase.database.DatabaseReference;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

public class SignInActivity extends BaseActivity {

    String id, password;
    FontEditText userId, userPassword;
    Button buttonSubmit;
    TextView buttonForgotPassword;
    RelativeLayout layoutSignIn;
    NavigationHeader mBackHeader;
    SharedPreferencesUtil preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userId = findViewById(R.id.input_id);
        userPassword = findViewById(R.id.request_user_password);
        buttonSubmit = findViewById(R.id.submit_button);
        buttonForgotPassword = findViewById(R.id.forgot_password);
        layoutSignIn = findViewById(R.id.layout_signin);
        mBackHeader = findViewById(R.id.back_header);
        mBackHeader.setUp(this, "Login");
        mBackHeader.setView("Login", this);
        preference = new SharedPreferencesUtil(getApplicationContext());

        // Creating toast messages.
        CharSequence failText = "Sorry please enter right information...";
        CharSequence sendEmail = "Your password has been send on your registered mail id...";
        final Activity activity = this;

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = userId.getText().toString().trim();
                password = userPassword.getText().toString().trim();
                Intent homeScreen = new Intent(getBaseContext(), MpinActivity.class);
                if ((id.isEmpty()) && (password.isEmpty())) {
                    displayToast(getApplicationContext(), failText);
                } else {
                    DialogUtil.yesDialog(activity, "Login", "Login Successful", click -> {
                        startActivity(homeScreen);
                        preference.putLoginWith("idPassword");
                    });
                }
            }
        });
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,ResetPassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        verifyNetwork();
        super.onResume();
        mBackHeader.setView("Login", this);
        mBackHeader.setUp(this, "Login");
    }
}
