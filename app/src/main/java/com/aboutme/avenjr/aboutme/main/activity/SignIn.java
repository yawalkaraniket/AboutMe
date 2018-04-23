package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.google.firebase.database.DatabaseReference;

import static com.aboutme.avenjr.aboutme.main.Utils.FireBaseUtil.getFireBaseReference;

public class SignIn extends AppCompatActivity {

    String id, password;
    EditText userId, userPassword;
    Button buttonSubmit, buttonForgotPassword;
    Toast successToast, failureToast, sendEmailToast;
    RelativeLayout layoutSignIn;
    private DatabaseReference mDatabaseReference;

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

        // Creating toast messages.
        Context context = getApplicationContext();
        CharSequence successText = "Successful!";
        CharSequence failText = "Sorry please enter right information...";
        CharSequence sendEmail = "Your password has been send on your registered mail id...";

        //  Adding back header
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View backHeader = layoutInflater.inflate(R.layout.backheader,layoutSignIn,true);

        int duration = Toast.LENGTH_SHORT;
        successToast = Toast.makeText(context, successText, duration);
        failureToast = Toast.makeText(context, failText, duration);
        sendEmailToast = Toast.makeText(context, sendEmail,duration);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = userId.getText().toString().trim();
                password = userPassword.getText().toString().trim();
                Intent homeScreen = new Intent(getBaseContext(), HomeScreen.class);
                if ((id.isEmpty()) && (password.isEmpty())) {
                    failureToast.show();
                } else {
//                    UserInformation userInformation = new UserInformation(id,password);
//                    saveInformation(userInformation,mDatabaseReference);
                    successToast.show();
                    startActivity(homeScreen);
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


}
