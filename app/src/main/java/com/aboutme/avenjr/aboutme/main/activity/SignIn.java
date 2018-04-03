package com.aboutme.avenjr.aboutme.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;

public class SignIn extends AppCompatActivity {

    String id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Context context = getApplicationContext();
        CharSequence successText = "Successful!";
        CharSequence failText = "Sorry please enter right information...";
        int duration = Toast.LENGTH_SHORT;
        final Toast successToast = Toast.makeText(context, successText, duration);
        final Toast failureToast = Toast.makeText(context, failText, duration);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText userId = findViewById(R.id.request_user_id);
        final EditText userPassword = findViewById(R.id.request_user_password);
        Button buttonSubmit = findViewById(R.id.submit_button);

        userId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId.setText("");
            }
        });
        userPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPassword.setText("");
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = userId.getText().toString();
                password = userPassword.getText().toString();
                Intent homeScreen = new Intent(getBaseContext(), HomeScreen.class);
                if ((id.isEmpty()) && (password.isEmpty())) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry please enter right information ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Success!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(homeScreen);
                }
            }
        });

    }

}
