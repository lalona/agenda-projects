package com.example.lalo10.agenda.List_Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lalo10.agenda.Helpers.AdjustmentHelper;
import com.example.lalo10.agenda.Helpers.CallbackAdjustTime;
import com.example.lalo10.agenda.Helpers.TimeDimenHelper;
import com.example.lalo10.agenda.NewProyect.CalActivities;
import com.example.lalo10.agenda.R;

import java.util.List;

/**
 * Created by lalo10 on 11/8/17.
 */ // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class CalActViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView schedule;
    public TextView goal;
    private View view;
    private Button btnAddMinToHour;
    private Button btnSubMinFromHour; // Button substract minute to hour
    private int yBegin;
    private CalActOrgAdapter calActOrgAdapter;
    private AdjustmentHelper adjuster;
    private RelativeLayout rlItemCalAct;

    private static final String TAG = CalActViewHolder.class.getSimpleName();

    public CalActViewHolder(View v) {

        super(v);
        this.adjuster = AdjustmentHelper.getOnlyInstance();
        this.schedule = v.findViewById(R.id.txtScheduleCalAct);
        this.goal = v.findViewById(R.id.txtGoalCalAct);
        this.btnAddMinToHour = v.findViewById(R.id.btnIncrementCalAct);
        this.btnSubMinFromHour = v.findViewById(R.id.btnDecremntCalAct);
        rlItemCalAct = v.findViewById(R.id.rl_item_cal_act);
        this.view = v;
    }

    public void addData(CalActivities calActivities, final int pos, Activity activity) {

        if (((pos + 1) % 2) == 0) {
            this.view.setBackgroundColor(activity.getResources().getColor(R.color.cal_act_1));
        } else {
            this.view.setBackgroundColor(activity.getResources().getColor(R.color.cal_act_2));
        }
        this.schedule.setText(calActivities.getSchedule());
        this.goal.setText(calActivities.getGoal());
        /*this.rlItemCalAct.getLayoutParams().height += TimeDimenHelper.getPxFromMin(calActivities.getMinDedicated(), activity);
        this.rlItemCalAct.requestLayout();*/

        btnSubMinFromHour.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = (int) event.getRawY();

                int position[] = new int[2];
                view.getLocationOnScreen(position);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "Button action down");
                        adjuster.touchStart(y, view, pos, AdjustmentHelper.Direction.DECRESE,
                                new CallbackAdjustTime() {
                                    @Override
                                    public void adjustHour(int min, List<SectionRow<String,CalActivities>> activitiesList, int pos, AdjustmentHelper.Direction direction,View viewNeigh) {
                                        SectionRow<String, CalActivities> activitie = activitiesList.get(pos);

                                        if(activitie.isRow()) {
                                            CalActivities calActivities = activitie.getRow();
                                            if (direction == AdjustmentHelper.Direction.DECRESE) {
                                                calActivities.addMinutesToToHour(min);
                                                schedule.setText(calActivities.getSchedule());
                                                SectionRow<String, CalActivities> activitieNeigh = activitiesList.get(pos - 1);
                                                if(activitieNeigh.isRow()) {
                                                    CalActivities calActivitiesNeigh = activitieNeigh.getRow();
                                                    calActivitiesNeigh.addMinutesToFromHour(min);
                                                    TextView schedulerNeight = viewNeigh.findViewById(R.id.txtScheduleCalAct);
                                                    schedulerNeight.setText(calActivitiesNeigh.getSchedule());
                                                }
                                            }
                                        }
                                    }
                                });
                        break;
                }
                return false;
            }
        });
        btnAddMinToHour.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = (int) event.getRawY();

                int position[] = new int[2];
                view.getLocationOnScreen(position);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "Button action down");
                        adjuster.touchStart(y, view, pos, AdjustmentHelper.Direction.INCRESE,
                                new CallbackAdjustTime() {
                                    @Override
                                    public void adjustHour(int min, List<SectionRow<String,CalActivities>> activitiesList, int pos, AdjustmentHelper.Direction direction, View viewNeigh) {
                                        SectionRow<String, CalActivities> activitie = activitiesList.get(pos);

                                        if(activitie.isRow()) {
                                            CalActivities calActivities = activitie.getRow();
                                            if(direction == AdjustmentHelper.Direction.INCRESE) {
                                                calActivities.addMinutesToFromHour(min);
                                                schedule.setText(calActivities.getSchedule());
                                                SectionRow<String, CalActivities> activitieNeigh = activitiesList.get(pos + 1);
                                                if(activitieNeigh.isRow()) {
                                                    CalActivities calActivitiesNeigh = activitieNeigh.getRow();
                                                    calActivitiesNeigh.addMinutesToToHour(min);
                                                    TextView schedulerNeight = viewNeigh.findViewById(R.id.txtScheduleCalAct);
                                                    schedulerNeight.setText(calActivitiesNeigh.getSchedule());
                                                }
                                            }
                                        }
                                    }
                                });
                        break;
                }
                return false;
            }
        });
    }

}
