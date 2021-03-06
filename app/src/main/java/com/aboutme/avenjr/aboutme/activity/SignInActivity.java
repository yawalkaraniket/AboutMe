package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

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
        layoutSignIn = findViewById(R.id.layout_sign_in);
        header = findViewById(R.id.navigation_header);
        header.setView("Login", this,true);
        preference = new SharedPreferencesUtil(getApplicationContext());

        // Creating toast messages.
        CharSequence failText = "Sorry please enter right information...";
        final Activity activity = this;

        buttonSubmit.setOnClickListener(v -> {
            id = userId.getText().toString().trim();
            password = userPassword.getText().toString().trim();
            Intent homeScreen = new Intent(getBaseContext(), MpinActivity.class);
            if ((id.isEmpty()) && (password.isEmpty())) {
                displayToast(getApplicationContext(), failText);
            } else {
                getFireBaseReference("UserInformation").orderByChild("email").equalTo(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                token = userSnapshot.getKey();
                                for (DataSnapshot programSnapshot : userSnapshot.getChildren()) {
                                    userInfo.put(programSnapshot.getKey(), Objects.requireNonNull(programSnapshot.getValue()).toString());
                                }
                            }
                            if (userInfo.get("password").equals(password)) {
                                DialogUtil.yesDialog(activity, "Login", "Login Successful", click -> {
                                    startActivity(homeScreen);
                                    preference.putLoginWith("email");
                                    preference.setName(userInfo.get("name"));
                                    preference.setEmail(userInfo.get("email"));
                                    preference.setMPin(userInfo.get("mpin"));
                                    preference.setProfileImageUrl("null");
                                    preference.setToken(userInfo.get("databaseKey"));
                                    preference.setAppRating(Float.valueOf(userInfo.get("rate")));
                                    activity.finish();
                                });
                            } else {
                                displayToast(getBaseContext(), "please check your password..");
                            }
                        } else {
                            DialogUtil.errorDialog(activity,"please check your id...");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        buttonForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(activity, ResetPassword.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        verifyNetwork();
        super.onResume();
        header.setView("Login", this,true);
        header.setUp(this, "Login");
    }
}
