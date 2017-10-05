package com.example.lalo10.agenda;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by lalo10 on 8/10/17.
 */

public class SplashActivity extends AppCompatActivity {

    private Handler endThisSuffer;
    private Runnable endIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        endIt = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        endThisSuffer = new Handler();
        endThisSuffer.postDelayed(endIt,1100);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                endThisSuffer.removeCallbacks(endIt);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

}
