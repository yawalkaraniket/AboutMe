package com.aboutme.avenjr.aboutme.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;

import butterknife.ButterKnife;

/**
 * Created by tudip on 28/5/18.
 */

public class DataRegistration extends RelativeLayout {

    public DataRegistration(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.add_content_layout, null);
        ButterKnife.bind(view);
        addView(view);
    }
}
