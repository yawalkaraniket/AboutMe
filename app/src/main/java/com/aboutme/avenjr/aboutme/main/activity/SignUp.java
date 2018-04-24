package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.main.Utils.FireBaseUtil;
import com.aboutme.avenjr.aboutme.main.view.BackHeader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    private RelativeLayout signUpView;
    private EditText emailId, password, passwordAgain, name, lastName, mobileNo;
    private Button signUp;
    private String userId, userPassword, userPasswordAgain, userName, userLastName, userMobileNo;
    DatabaseReference mDatabaseReference;
    Context mContext;
    Toast successToast, failToast;
    private int duration;
    CharSequence success, fail;
    FirebaseAuth mFirebaseAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mOnVerificationStateChangedCallbacks;
    BackHeader backHeader;

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
        mobileNo = findViewById(R.id.request_user_mobile_number);
        backHeader =  findViewById(R.id.back_header);

        mDatabaseReference = FireBaseUtil.getFireBaseReference("UserInformation");
        mContext = getApplicationContext();
        success = "success";
        fail = "Please enter correct credentials....";
        duration = Toast.LENGTH_SHORT;
        successToast = Toast.makeText(mContext, success, duration);
        failToast = Toast.makeText(mContext, fail, duration);
        mFirebaseAuth = FirebaseAuth.getInstance();
        backHeader.setUp(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = emailId.getText().toString().trim();
                userPassword = password.getText().toString().trim();
                userPasswordAgain = passwordAgain.getText().toString().trim();
                userName = name.getText().toString().trim();
                userLastName = lastName.getText().toString().trim();
                userMobileNo = mobileNo.getText().toString().trim();

                if (userPassword.equals(userPasswordAgain) && !userId.isEmpty() && !userName.isEmpty() && !userLastName.isEmpty() && !userPassword.isEmpty() && !userMobileNo.isEmpty()) {
                    UserInformation userInformation = new UserInformation(userId, userPassword, userName, userLastName, userMobileNo);

                    mOnVerificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                        }

                        @Override
                        public void onVerificationFailed(FirebaseException e) {

                        }

                        @Override
                        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            send_code();
                            super.onCodeSent(s, forceResendingToken);
                        }
                    };
                    FireBaseUtil.saveInformation(userInformation, mDatabaseReference);
                    successToast.show();
                } else {
                    failToast.show();
                }
            }
        });
    }
    public void send_code() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(userMobileNo,60, TimeUnit.SECONDS, this,
                mOnVerificationStateChangedCallbacks);
    }

    public void sign_in_withMobileNo(PhoneAuthCredential credential) {
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            successToast.show();
                        }
                    }
                });
    }

}
