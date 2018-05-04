package com.aboutme.avenjr.aboutme.main.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;

/**
 * Created by tudip on 1/5/18.
 */

public class DialogUtil {
    public Dialog yesCancelDialog(Activity activity, String titleText, String descriptionText,
                                  final View.OnClickListener positiveClick,
                                  final View.OnClickListener negativeClick){
        View view = activity.getLayoutInflater().inflate(R.layout.dialog,null);

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
        View view = activity.getLayoutInflater().inflate(R.layout.yes_dialog,null);

        TextView dialogHeaderText  =  view.findViewById(R.id.dialog_header);
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
                if(yesButtonclick!=null) {
                    yesButtonclick.onClick(v);
                }
            }
        });

        return dialog;
    }
}
