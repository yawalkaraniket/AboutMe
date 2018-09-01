package com.aboutme.avenjr.aboutme.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.databinding.ActivityViewBindingExampleBinding;
import com.aboutme.avenjr.aboutme.view.DialogUtil;

public class ViewBindingExample extends AppCompatActivity {

    private ActivityViewBindingExampleBinding mActivityViewBindingExample;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setClickListener();
        mActivity = this;
    }

    private void setClickListener() {
        mActivityViewBindingExample.buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.errorDialog(mActivity,"sdfsdfsd");
            }
        });
    }

    private void init() {
        mActivityViewBindingExample = DataBindingUtil.setContentView(this,R.layout.activity_view_binding_example);
    }
}
