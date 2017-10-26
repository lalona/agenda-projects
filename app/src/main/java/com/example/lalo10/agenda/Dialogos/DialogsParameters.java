package com.example.lalo10.agenda.Dialogos;

import android.app.Activity;
import android.content.DialogInterface;


/**
 * Created by lalo10 on 7/27/17.
 */

public abstract class DialogsParameters {
    public Activity activity;
    public DialogsParameters(Activity activity) {
        this.activity = activity;
    }
    public abstract void yesCall(DialogInterface dialog);
    public abstract void noCall();
    public abstract int getMessage();
    public abstract int getTitle();
}
