package com.aboutme.avenjr.aboutme.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.activity.RecyclerViewExample;

/**
 * Created by AvenjR on 28/5/18.
 */

public class DataRegistration extends RelativeLayout {

    FontEditText blogId, blogPassword;
    RelativeLayout blogRegistrationLayout, blankRegistrationLayout;
    ImageView addButton;
    Button submitRegistration;
    Context context;

    String inputBlogId = null, inputBlogPassword = null;

    public DataRegistration(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.add_content_layout, null);
        blogId = view.findViewById(R.id.blog_id);
        blogPassword = view.findViewById(R.id.blog_password);
        blogRegistrationLayout = view.findViewById(R.id.blog_registration);
        blankRegistrationLayout = view.findViewById(R.id.registration_blank);
        addButton = view.findViewById(R.id.add_button);
        submitRegistration = view.findViewById(R.id.blog_registration_submit);
        this.context = context;
        blogPassword.setCursorVisible(false);
        addView(view);
    }

    public void setUp(Activity activity) {

        if (!(inputBlogId == null) && !(inputBlogPassword == null)) {
            blogRegistrationLayout.setVisibility(VISIBLE);
        } else {
            blogRegistrationLayout.setVisibility(GONE);
            blankRegistrationLayout.setVisibility(VISIBLE);
            addButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    blogRegistrationLayout.setVisibility(VISIBLE);
                    blankRegistrationLayout.setVisibility(GONE);
                    inputBlogId = blogId.getText().toString();
                    inputBlogPassword = blogPassword.getText().toString();
                }
            });
        }

        submitRegistration.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerViewExample.class);
                context.startActivity(intent);
            }
        });
    }

    public static void showKeyboard(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
