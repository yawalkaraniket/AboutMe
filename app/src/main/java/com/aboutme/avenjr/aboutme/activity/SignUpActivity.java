package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.FireBaseUtil;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.request_user_id)
    FontEditText emailId;

    @BindView(R.id.input_password)
    FontEditText password;

    @BindView(R.id.request_user_repassword)
    FontEditText passwordAgain;

    @BindView(R.id.request_user_name)
    FontEditText name;

    @BindView(R.id.select_country)
    Spinner selectCountry;

    @BindView(R.id.button_sign_up)
    Button signUp;

    @BindView(R.id.navigation_header)
    NavigationHeader header;
    
    @BindView(R.id.agree_terms_and_condition)
    TextView termsAndCondition;

    private String userId, userPassword, userPasswordAgain, userName;
    DatabaseReference mDatabaseReference;
    Context mContext;
    CharSequence success, fail;
    FirebaseAuth mFirebaseAuth;
    Activity activity = this;
    public static boolean setMpin;
    UserInformation mUserInformation = new UserInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        //  Adding a views
        termsAndCondition.setMovementMethod(LinkMovementMethod.getInstance());
        termsAndCondition.setText(addSpannableText(),TextView.BufferType.SPANNABLE);
        mDatabaseReference = FireBaseUtil.getFireBaseReference("UserInformation");
        mContext = getApplicationContext();
        success = "success";
        fail = "Please enter correct credentials....";
        mFirebaseAuth = FirebaseAuth.getInstance();
        header.setUp(this, "SignUp");
        header.setView("SignUp", this);
        selectSpinnerSetUp();
        addSpannableText();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = emailId.getText().toString().trim();
                userPassword = password.getText().toString().trim();
                userPasswordAgain = passwordAgain.getText().toString().trim();
                userName = name.getText().toString().trim();

                if (verifyRenterPassword(userPassword, userPasswordAgain) && !userId.isEmpty() && !userName.isEmpty()) {
                    UserInformation userInformation = new UserInformation(userId, userPassword, userName);

                    FireBaseUtil.saveInformation(userInformation, mDatabaseReference);
                    DialogUtil.yesDialog(activity, "SignUp", "you are registered please \n conform your mobile number..", click -> {
                        Intent intent = new Intent(getBaseContext(), MobileAuthenticationActivity.class);
                        startActivity(intent);
                        setMpin = true;
                    });
                } else {
                    displayToast(getApplicationContext(), fail);
                }
            }
        });

        selectCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mUserInformation.setCountry("India");
                        mUserInformation.setCountryCode("+91");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private SpannableStringBuilder addSpannableText() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("After submit, you are agree ");
        spannableStringBuilder.append(getString(R.string.terms_of_service));
        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(activity,WebviewActivity.class);
                intent.putExtra("header",getString(R.string.terms_of_service));
                startActivity(intent);
            }
        },spannableStringBuilder.length()-getString(R.string.terms_of_service).length(),spannableStringBuilder.length(),0);
        spannableStringBuilder.append(" and ");
        spannableStringBuilder.append(getString(R.string.privacy_policy));
        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(activity,WebviewActivity.class);
                intent.putExtra("header",getString(R.string.privacy_policy));
                startActivity(intent);
            }
        },spannableStringBuilder.length()-getString(R.string.privacy_policy).length(),spannableStringBuilder.length(),0);
        return spannableStringBuilder;
    }

    private void selectSpinnerSetUp() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.select_country,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCountry.setAdapter(adapter);
    }

    public boolean verifyRenterPassword(String userPassword, String userPasswordAgain) {
        return userPassword.equals(userPasswordAgain) && !userPassword.isEmpty() && !userPasswordAgain.isEmpty();
    }
}
