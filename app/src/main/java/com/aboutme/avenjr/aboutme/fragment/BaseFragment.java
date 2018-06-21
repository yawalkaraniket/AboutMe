package com.aboutme.avenjr.aboutme.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.activity.BaseActivity;

public class BaseFragment extends Fragment {

    Toast toast;

    protected void setupProgress(RelativeLayout layout) {
        ((BaseActivity) getActivity()).setupProgress(layout);
    }

    protected void showProgress() {
        ((BaseActivity) getActivity()).showProgress();
    }

    protected void showProgress(int type) {
        ((BaseActivity) getActivity()).showProgress(type);
    }

    protected void showProgress(boolean blockScreen) {
        ((BaseActivity) getActivity()).showProgress(blockScreen);
    }

    protected void hideProgress() {
        ((BaseActivity) getActivity()).hideProgress();
    }

    public void displayToast(Context context, CharSequence message){
        toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }
}
