package com.example.lalo10.agenda.Dialogos;

import android.app.Activity;


/**
 * Created by lalo10 on 7/27/17.
 */

public abstract class DialogsParameters {
    public Activity activity;
    public DialogsParameters(Activity activity) {
        this.activity = activity;
    }
    public abstract void yesCall();
    public abstract void noCall();
    public abstract int getMessage();
    public abstract int getTitle();
}
