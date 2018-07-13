package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

public class SignInActivity extends BaseActivity {

    String id, password;
    FontEditText userId, userPassword;
    Button buttonSubmit;
    TextView buttonForgotPassword;
    RelativeLayout layoutSignIn;
    NavigationHeader header;
    SharedPreferencesUtil preference;
    String token;
    HashMap<String, String> userInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userId = findViewById(R.id.input_id);
        userPassword = findViewById(R.id.request_user_password);
        buttonSubmit = findViewById(R.id.submit_button);
        buttonForgotPassword = findViewById(R.id.forgot_password);
        layoutSignIn = findViewById(R.id.layout_signin);
        header = findViewById(R.id.navigation_header);
        header.setView("Login", this);
        preference = new SharedPreferencesUtil(getApplicationContext());

        // Creating toast messages.
        CharSequence failText = "Sorry please enter right information...";
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
                    getFireBaseReference("UserInformation").orderByChild("email").equalTo(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    token = userSnapshot.getKey().toString();
                                    for (DataSnapshot programSnapshot : userSnapshot.getChildren()) {
                                        userInfo.put(programSnapshot.getKey().toString(), programSnapshot.getValue().toString());
                                    }
                                }
                                if (userInfo.get("password").equals(password)) {
                                    DialogUtil.yesDialog(activity, "Login", "Login Successful", click -> {
                                        startActivity(homeScreen);
                                        preference.putLoginWith("email");
                                        preference.setName(userInfo.get("name")+" "+userInfo.get("lastName"));
                                        preference.setEmail(userInfo.get("email"));
                                        preference.setProfileImageUrl("null");
                                    });
                                } else {
                                    displayToast(getBaseContext(), "please check your password..");
                                }
                            } else {
                                displayToast(getBaseContext(), "please check your id...");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ResetPassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        verifyNetwork();
        super.onResume();
        header.setView("Login", this);
        header.setUp(this, "Login");
    }
}
