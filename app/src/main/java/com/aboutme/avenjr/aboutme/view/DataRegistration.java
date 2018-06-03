package com.aboutme.avenjr.aboutme.view;

import android.content.Context;
import android.inputmethodservice.ExtractEditText;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;

/**
 * Created by AvenjR on 28/5/18.
 */

public class DataRegistration extends RelativeLayout {

    FontEditText blogId, blogPassword;
    RelativeLayout blogRegistrationLayout, blankRegistrationLayout;
    ImageView addButton;

    String inputBlogId = null, inputBlogPassword = null;

    public DataRegistration(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.add_content_layout, null);
        blogId = view.findViewById(R.id.blog_id);
        blogPassword = view.findViewById(R.id.blog_password);
        blogRegistrationLayout = view.findViewById(R.id.blog_registration);
        blankRegistrationLayout = view.findViewById(R.id.registration_blank);
        addButton = view.findViewById(R.id.add_button);
        blogPassword.setCursorVisible(false);
        addView(view);
    }

    public void setUp() {

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
