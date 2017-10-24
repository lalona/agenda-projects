package com.example.lalo10.agenda.Helpers;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.View;

/**
 * Created by lalo10 on 10/11/17.
 */

public class SvgHelper {

    public static void setIcon(Activity activity, int resourceDrawable, View b) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            drawable = activity.getResources().getDrawable(resourceDrawable, activity.getTheme());
        } else {
            drawable = VectorDrawableCompat.create(activity.getResources(), resourceDrawable, activity.getTheme());
        }
        if(b != null)
            b.setBackground(drawable);
    }

}
