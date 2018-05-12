package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;
import com.google.firebase.database.DatabaseReference;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

public class SignInActivity extends BaseActivity {

    String id, password;
    EditText userId, userPassword;
    Button buttonSubmit, buttonForgotPassword;
    Toast successToast, failureToast, sendEmailToast;
    RelativeLayout layoutSignIn;
    private DatabaseReference mDatabaseReference;
    NavigationHeader mBackHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userId = findViewById(R.id.request_user_id);
        userPassword = findViewById(R.id.request_user_password);
        buttonSubmit = findViewById(R.id.submit_button);
        buttonForgotPassword = findViewById(R.id.forgot_password);
        layoutSignIn = findViewById(R.id.layout_signin);
        mDatabaseReference = getFireBaseReference("userInfo");
        mBackHeader = findViewById(R.id.back_header);

        mBackHeader.setUp(this,"Login");
        mBackHeader.setView("Login");

        // Creating toast messages.
        final Context context = getApplicationContext();
        CharSequence successText = "Successful!";
        CharSequence failText = "Sorry please enter right information...";
        CharSequence sendEmail = "Your password has been send on your registered mail id...";

        int duration = Toast.LENGTH_SHORT;
        successToast = Toast.makeText(context, successText, duration);
        failureToast = Toast.makeText(context, failText, duration);
        sendEmailToast = Toast.makeText(context, sendEmail,duration);
        final Activity activity = this;

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = userId.getText().toString().trim();
                password = userPassword.getText().toString().trim();
                Intent homeScreen = new Intent(getBaseContext(), HomeScreen.class);
                if ((id.isEmpty()) && (password.isEmpty())) {
                    failureToast.show();
                } else {
                    DialogUtil.yesDialog(activity,"Login","Login Successful",click->{
                        startActivity(homeScreen);
                    });
                    successToast.show();
                }
            }
        });
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailToast.show();
            }
        });
    }


    @Override
    protected void onResume() {
        if(!isConnectedToInternet())
            netWorkErrorDialog();
        super.onResume();
        mBackHeader.setView("Login");
        mBackHeader.setUp(this,"Login");
    }
}
