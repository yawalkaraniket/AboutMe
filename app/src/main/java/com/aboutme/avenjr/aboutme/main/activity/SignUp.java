package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.main.Utils.FireBaseUtil;
import com.google.firebase.database.DatabaseReference;

public class SignUp extends AppCompatActivity {

    private RelativeLayout signUpView;
    private EditText emailId, password, passwordAgain, name, lastName;
    private Button signUp;
    private String userId, userPassword, userPasswordAgain, userName, userLastName;
    DatabaseReference mDatabaseReference;
    Context mContext;
    Toast successToast, failToast;
    private int duration;
    CharSequence success, fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //  Adding a views
        signUpView = findViewById(R.id.signup_view);
        emailId = findViewById(R.id.request_user_id);
        password = findViewById(R.id.request_user_password);
        passwordAgain = findViewById(R.id.request_user_repassword);
        name = findViewById(R.id.request_user_name);
        lastName = findViewById(R.id.request_user_lastName);
        signUp = findViewById(R.id.button_sign_up);
        mDatabaseReference = FireBaseUtil.getFireBaseReference("UserInformation");
        mContext = getApplicationContext();
        success = "success";
        fail = "Please enter correct credentials....";
        duration = Toast.LENGTH_SHORT;
        successToast = Toast.makeText(mContext, success, duration);
        failToast = Toast.makeText(mContext, fail, duration);


        //  Adding the back header
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View backHeader = layoutInflater.inflate(R.layout.backheader, signUpView, true);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = emailId.getText().toString().trim();
                userPassword = password.getText().toString().trim();
                userPasswordAgain = passwordAgain.getText().toString().trim();
                userName = name.getText().toString().trim();
                userLastName = lastName.getText().toString().trim();

                if (userPassword.equals(userPasswordAgain) && !userId.isEmpty() && !userName.isEmpty() && !userLastName.isEmpty() && !userPassword.isEmpty()) {
                    UserInformation userInformation = new UserInformation(userId, userPassword, userName, userLastName);
                    FireBaseUtil.saveInformation(userInformation, mDatabaseReference);
                    successToast.show();
                } else {
                    failToast.show();
                }
            }
        });
    }
}
