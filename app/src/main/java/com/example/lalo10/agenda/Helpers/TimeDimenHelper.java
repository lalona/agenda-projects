package com.example.lalo10.agenda.Helpers;

import android.app.Activity;

/**
 * Created by lalo10 on 10/27/17.
 */

public class TimeDimenHelper {



    // Por cada minuto va a haber 5 dp
    private static final int NUM_DP_PER_MIN = 5; // cada 1 minutos son 5 dp

    public static final int MIN_MINUTES_TIME = 20; // 20 minutos es el minimo de tiempo para una actividad

    public static int getPxFromMin(int min, Activity activity) {
        return DimenHelper.dpToPx((min * NUM_DP_PER_MIN), activity);
    }

    public static int getMinFromPx(int px, Activity activity) {
        return DimenHelper.pxToDp(px, activity) / NUM_DP_PER_MIN;
    }



}
