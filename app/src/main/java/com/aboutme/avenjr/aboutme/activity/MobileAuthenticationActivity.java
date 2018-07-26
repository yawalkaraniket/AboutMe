package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MobileAuthenticationActivity extends BaseActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.request_user_mobile_number)
    FontEditText requestMobileNumber;

    @BindView(R.id.send_button)
    Button buttonSend;

    @BindView(R.id.verify_code)
    FontEditText verificationCode;

    @BindView(R.id.mobile_verification_parent)
    RelativeLayout mobileVerificationParent;

    @BindView(R.id.otp_verification_layout)
    RelativeLayout verificationLayout;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mOnVerificationStateChangedCallbacks;
    String mobileNumber, inputVerificationCode, authCredentials;
    UserInformation userInfo = new UserInformation();
    Activity activity = this;
    SharedPreferencesUtil preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_authentication);
        ButterKnife.bind(this);
        preferences = new SharedPreferencesUtil(getApplicationContext());
        header.setView(getString(R.string.mobile_verification), this,true);
        setupProgress(mobileVerificationParent);
        mOnVerificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                verificationLayout.setVisibility(View.VISIBLE);
                verificationCode.setText(phoneAuthCredential.getSmsCode());
                authCredentials = phoneAuthCredential.getSmsCode();
                inputVerificationCode = verificationCode.getText().toString();
                if (inputVerificationCode.equals(phoneAuthCredential.getSmsCode())) {
                    displayToast(getApplicationContext(), getString(R.string.success_message));
                    DialogUtil.yesDialog(activity, getString(R.string.confirm_message), getString(R.string.mobile_number_registration_successful),
                            click -> {
                        Intent intent = new Intent(getApplicationContext(), MpinActivity.class);
                        startActivity(intent);
                        preferences.putLoginWith("SignUp");
                        preferences.setName(userInfo.getName());
                        preferences.setEmail(userInfo.getEmail());
                        preferences.setMobileNumber(mobileNumber);
                        userInfo.setMobileNo(mobileNumber);
                    });
                }
                hideProgress();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                hideProgress();
                displayToast(getApplicationContext(), getString(R.string.mobile_verification_failed));
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                hideProgress();
            }
        };
    }

    @OnClick(R.id.send_button)
    public void sendMobileVerificationRequest() {
        mobileNumber = requestMobileNumber.getText().toString();
        send_code();
        showProgress();
    }

    public void send_code() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(userInfo.getCountryCode() +
                        mobileNumber, 60, TimeUnit.SECONDS, this,
                mOnVerificationStateChangedCallbacks);
    }

    @OnClick(R.id.verify_code_button)
    public void verifyCode() {
        if (authCredentials.equals(inputVerificationCode)) {
            DialogUtil.yesDialog(this, getString(R.string.confirm_message), getString(R.string.mobile_number_registration_successful), click -> {
                preferences.setMobileNumber(mobileNumber);
                Intent intent = new Intent(this, MpinActivity.class);
                startActivity(intent);
            });
        } else {
            displayToast(getApplicationContext(), getString(R.string.enter_correct_otp));
        }
    }
}
