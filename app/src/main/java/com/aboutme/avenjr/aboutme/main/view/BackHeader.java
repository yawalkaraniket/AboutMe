package com.aboutme.avenjr.aboutme.main.view;

import android.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tudip on 23/4/18.
 */

public class BackHeader extends RelativeLayout {

    @BindView(R.id.back_header_right)
    ImageButton backHeader;

    public BackHeader(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.backheader,null);
        ButterKnife.bind(this,view);
    }

    public void setUp(final Fragment fragment) {
          backHeader.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View v) {
                  fragment.getFragmentManager().popBackStack();
              }
          });
    }
}
