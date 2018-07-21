package com.aboutme.avenjr.aboutme.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.FireBaseUtil;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.aboutme.avenjr.aboutme.activity.HomeScreen;
import com.aboutme.avenjr.aboutme.activity.UserInformation;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

/**
 * Created by AvenjR on 26/5/18.
 */

public class Mpin extends RelativeLayout {

    ImageView one, two, three, four, five, six, seven, eight, nine, zero;
    TextView mPinError;
    int selectedCount = 0;
    ArrayList<Integer> mPin = new ArrayList<>();
    ArrayList<Integer> mPinVerify = new ArrayList<>();
    ArrayList<Integer> mPinConform = new ArrayList<>();
    Context context;
    private boolean wantToConfirm = false;
    DatabaseReference mDatabaseReference = getFireBaseReference("UserInformation");
    ;
    UserInformation mUserInformation = new UserInformation();
    SharedPreferencesUtil preferences;

    public void setPinVerify(ArrayList<Integer> pinVerify) {
        mPinVerify = pinVerify;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public Mpin(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.mpin, null);
        ButterKnife.bind(view);
        addView(view);
        this.context = context;

        preferences = new SharedPreferencesUtil(context);
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
        mPinError = findViewById(R.id.mPin_error);

        setSelectListener(zero, 0);
        setSelectListener(one, 1);
        setSelectListener(two, 2);
        setSelectListener(three, 3);
        setSelectListener(four, 4);
        setSelectListener(five, 5);
        setSelectListener(six, 6);
        setSelectListener(seven, 7);
        setSelectListener(eight, 8);
        setSelectListener(nine, 9);

        mPinVerify.add(1);
        mPinVerify.add(2);
        mPinVerify.add(3);
        mPinVerify.add(4);
        setPinVerify(mPinVerify);
    }

    public void setSelectionView(ImageView imageView, int number) {
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.selected_circle));
        imageView.setSelected(true);
        selectedCount++;
        if (wantToConfirm) {
            mPinConform.add(number);
        }
        if (!wantToConfirm) {
            mPin.add(number);
        }
    }

    public void setSelectListener(ImageView imageView, int number) {
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPinError.setVisibility(GONE);
                if (imageView.isSelected()) {
                    removeSelectionView(imageView);
                } else {
                    setSelectionView(imageView, number);
                    if (preferences.getMPin().equals(getResources().getString(R.string.mpin))) {
                        if (getSelectedCount() == 4) {
                            if (wantToConfirm && mPin.equals(mPinConform)) {
                                wantToConfirm = false;
                                mUserInformation.setMPin(mPin.toString());
                                FireBaseUtil.saveInformation(mUserInformation, mDatabaseReference);
                                Intent intent = new Intent(context, HomeScreen.class);
                                intent.putExtra("login_with", "signIn");
                                context.startActivity(intent);
                            }
                            clearAllSelectedView();
                            wantToConfirm = true;
                        }
                    }
                    if (getSelectedCount() == 4 && mPin.toString().equals(preferences.getMPin()) && !preferences.getMPin().equals(getResources().getString(R.string.mpin))) {
                        Intent intent = new Intent(context, HomeScreen.class);
                        intent.putExtra("login_with", "signIn");
                        context.startActivity(intent);
                    } else if (getSelectedCount() == 4) {
                        clearAllSelectedView();
                        mPin.clear();
                        mPinError.setVisibility(VISIBLE);
                    }
                }
            }
        });
    }

    private void clearAllSelectedView() {
        one.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        two.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        three.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        four.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        five.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        six.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        seven.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        eight.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        nine.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        zero.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        one.setSelected(false);
        two.setSelected(false);
        three.setSelected(false);
        four.setSelected(false);
        five.setSelected(false);
        six.setSelected(false);
        seven.setSelected(false);
        eight.setSelected(false);
        nine.setSelected(false);
        zero.setSelected(false);
        selectedCount = 0;
        mPinConform.clear();
    }

    public void removeSelectionView(ImageView imageView) {
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        imageView.setSelected(false);
        selectedCount--;
        if (wantToConfirm) {
            mPinConform.remove(mPinConform.size() - 1);
        } else {
            mPin.remove(mPin.size() - 1);
        }
    }
}
