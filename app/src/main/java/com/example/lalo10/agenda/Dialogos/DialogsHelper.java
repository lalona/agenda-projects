package com.example.lalo10.agenda.Dialogos;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;


import com.example.lalo10.agenda.R;

import java.util.List;

/**
 * Created by lalo10 on 7/27/17.
 */

public class DialogsHelper {

    public static void showQuestionDialog(final Activity activity, final DialogsParameters dialogsParameters) {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(activity);
        a_builder.setMessage(dialogsParameters.getMessage())
                .setCancelable(false)
                .setPositiveButton(R.string.yes,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogsParameters.yesCall(dialog);
                        //dialog.cancel();
                    }
                })
                .setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogsParameters.noCall();
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle(dialogsParameters.getTitle());
        alert.show();
    }

    public static void showAlert(final Activity activity, final DialogsAlert dialogsParameters) {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(activity);
        a_builder.setMessage(dialogsParameters.getMessage())
                .setCancelable(false)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogsParameters.actionAfterOk();
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle(dialogsParameters.getTitle());
        alert.show();
    }


}
