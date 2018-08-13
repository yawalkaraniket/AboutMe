package com.aboutme.avenjr.aboutme.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.Utils.SharedPreferencesUtil;
import com.google.firebase.database.DatabaseReference;

import static com.aboutme.avenjr.aboutme.Utils.FireBaseUtil.getFireBaseReference;

/**
 * Created by tudip on 1/5/18.
 */

public class DialogUtil {

    Activity activity;

    public Dialog yesCancelDialog(Activity activity, String titleText, String descriptionText,
                                  final View.OnClickListener positiveClick,
                                  final View.OnClickListener negativeClick) {
        View view = activity.getLayoutInflater().inflate(R.layout.dialog, null);

        TextView dialogHeader = view.findViewById(R.id.dialog_header);
        TextView dialogDescription = view.findViewById(R.id.dialog_description);
        View yes = view.findViewById(R.id.layout_dialog_yes);
        View cancel = view.findViewById(R.id.layout_dialog_cancel);

        final android.app.Dialog dialog = new android.app.Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view);
        dialog.show();
        dialogHeader.setText(titleText);
        dialogDescription.setText(descriptionText);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static Dialog yesDialog(Activity activity, String titleText, String descriptionText, final View.OnClickListener yesButtonclick) {
        View view = activity.getLayoutInflater().inflate(R.layout.yes_dialog, null);

        TextView dialogHeaderText = view.findViewById(R.id.dialog_header);
        TextView dialogDescritionText = view.findViewById(R.id.dialog_description);
        View yesButton = view.findViewById(R.id.layout_dialog_yes);
        final android.app.Dialog dialog = new android.app.Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view);
        dialog.show();
        dialogHeaderText.setText(titleText);
        dialogDescritionText.setText(descriptionText);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (yesButtonclick != null) {
                    yesButtonclick.onClick(v);
                }
            }
        });
        return dialog;
    }

    public static Dialog noNetworkDialog(Activity activity) {

        View view = activity.getLayoutInflater().inflate(R.layout.no_network, null);

        View refreshButton = view.findViewById(R.id.button_refresh);
        final android.app.Dialog dialog = new android.app.Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view);
        dialog.show();
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet(activity)) {
                    dialog.dismiss();
                }
            }
        });

        return dialog;
    }

    public static void errorDialog(Activity activity,String message) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.error_dialog, null);

        AppTextView text = layout.findViewById(R.id.error_string);
        text.setText(message);

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    public static Dialog rateMe(Activity activity, String titleText, final View.OnClickListener yesButtonclick) {
        View view = activity.getLayoutInflater().inflate(R.layout.rate_me, null);

        SharedPreferencesUtil preferences = new SharedPreferencesUtil(activity.getApplicationContext());
        DatabaseReference databaseReference = getFireBaseReference("UserInformation/" + preferences.getToken() + "/rate");
        TextView dialogHeaderText = view.findViewById(R.id.dialog_header);
        RatingBar rating = view.findViewById(R.id.rate_me);
        rating.setRating(Float.parseFloat(preferences.getAppRating()));
        View yesButton = view.findViewById(R.id.layout_dialog_yes);
        final android.app.Dialog dialog = new android.app.Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view);
        dialog.show();
        dialogHeaderText.setText(titleText);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                preferences.setAppRating(rating.getRating());
                if (yesButtonclick != null) {
                    yesButtonclick.onClick(v);
                }
            }
        });
        return dialog;
    }


    private static boolean isConnectedToInternet(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
