package com.aboutme.avenjr.aboutme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.DialogUtil;
import com.aboutme.avenjr.aboutme.view.FontEditText;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.aboutme.avenjr.aboutme.activity.MainActivity.mAuth;

public class ResetPassword extends BaseActivity {

    @BindView(R.id.request_user_id)
    FontEditText requestUserId;

    @BindView(R.id.send_button)
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.send_button)
    public void send() {
        DialogUtil.yesDialog(activity, "Success!", "please press yes to reset your password...", click -> {
            mAuth.sendPasswordResetEmail(requestUserId.getText().toString());
        });
    }
}
