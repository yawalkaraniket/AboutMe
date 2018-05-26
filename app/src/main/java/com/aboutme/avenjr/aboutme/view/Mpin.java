package com.aboutme.avenjr.aboutme.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.activity.HomeScreen;

import butterknife.ButterKnife;



/**
 * Created by AvenjR on 26/5/18.
 */

public class Mpin extends RelativeLayout {

    ImageView one,two,three,four,five,six,seven,eight,nine,zero;

    public int getSelectedCount() {
        return selectedCount;
    }

    int selectedCount = 0;
    Context context;

    public Mpin(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.mpin,null);
        ButterKnife.bind(view);
        addView(view);
        this.context = context;

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);

        setSelectListener(zero);
        setSelectListener(one);
        setSelectListener(two);
        setSelectListener(three);
        setSelectListener(four);
        setSelectListener(five);
        setSelectListener(six);
        setSelectListener(seven);
        setSelectListener(eight);
        setSelectListener(nine);

    }

    public void setSelectionView(ImageView imageView){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.selected_circle));
        imageView.setSelected(true);
    }

    public void setSelectListener(ImageView imageView) {
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageView.isSelected()){
                    removeSelectionView(imageView);
                }else{
                    setSelectionView(imageView);
                    selectedCount++;
                    if(getSelectedCount()==4){
                        Intent intent = new Intent(context, HomeScreen.class);
                        context.startActivity(intent);
                    }
                }
            }
        });
    }

    public void removeSelectionView(ImageView imageView){
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        imageView.setSelected(false);
    }
}
