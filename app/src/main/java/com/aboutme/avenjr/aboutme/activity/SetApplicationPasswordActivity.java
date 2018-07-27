package com.aboutme.avenjr.aboutme.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.aboutme.avenjr.aboutme.view.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetApplicationPasswordActivity extends AppCompatActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader header;

    @BindView(R.id.request_user_password)
    FontEditText password;

    @BindView(R.id.request_user_repassword)
    FontEditText rePassword;

    @BindView(R.id.submit_button)
    Button submit;

    @BindView(R.id.select_country)
    Spinner selectCountry;

    UserInformation userInfo;
    SharedPreferencesUtil preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_application_password);
        ButterKnife.bind(this);

        header.setView("Set Password", this, true);
        preferences = new SharedPreferencesUtil(getApplicationContext());
        userInfo = new UserInformation();
        selectSpinnerSetUp();
        selectCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        userInfo.setCountry("India");
                        userInfo.setCountryCode("+91");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.submit_button)
    public void setPassword() {
        if (password.getText().toString().equals(rePassword.getText().toString())) {
            DialogUtil.yesDialog(this, getString(R.string.success_message),
                    "click yes button to save your password...", click -> {
                        preferences.putLoginWith("SignUp");
                        preferences.setPassword(password.getText().toString());
                        userInfo.setPassword(password.getText().toString());
                        Intent intent = new Intent(this, MpinActivity.class);
                        startActivity(intent);
                    });
        }
    }

    private void selectSpinnerSetUp() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.select_country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCountry.setAdapter(adapter);
    }
}
