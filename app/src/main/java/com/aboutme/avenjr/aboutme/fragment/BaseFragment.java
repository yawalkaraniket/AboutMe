package com.aboutme.avenjr.aboutme.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.activity.BaseActivity;

public class BaseFragment extends Fragment {

    Toast toast;

    protected void setupProgress(RelativeLayout layout) {
        ((BaseActivity) getActivity()).setupProgress(layout);
    }

    public void replaceFragment(Object object) {
        Fragment fragment = (Fragment) object;
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager.getFragments().get(manager.getFragments().size()-1).getClass().
                equals(object.getClass())){
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.profile_blank_fragment, fragment);
        transaction.addToBackStack(object.getClass().getSimpleName());
        transaction.commit();
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
