package com.example.lalo10.agenda.List_Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lalo10.agenda.NewProyect.CalActivities;
import com.example.lalo10.agenda.R;

import java.util.List;

/**
 * Calendar activity organize adapter
 * Created by lalo10 on 10/27/17.
 */

public class CalActOrgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int IS_ROW = 0;
    private static final int IS_SECTION = 1;
    private Activity activity;
    private int lastDiff;

    private static final String TAG = CalActOrgAdapter.class.getSimpleName();

    public View getViewAlargin() {
        return viewAlargin;
    }

    public void setViewAlargin(View viewAlargin) {
        this.viewAlargin = viewAlargin;
    }

    private View viewAlargin;

    public int getBeginY() {
        return beginY;
    }

    public void setBeginY(int beginY) {
        this.beginY = beginY;
        lastDiff = -1;
    }

    private int beginY;

    public boolean alargeView(int diff) {
        if(viewAlargin == null)
            return false;
        Log.d(TAG,"BeginY = " + beginY + "CurrentY = " + diff + " Height = "  + viewAlargin.getLayoutParams().height);

        if(diff > (lastDiff + 50) || diff < (lastDiff - 50)) {
            viewAlargin.getLayoutParams().height += diff - getBeginY();
            viewAlargin.requestLayout();
            lastDiff = diff;
            return true;
        }
        return true;
    }

    public boolean isAlargeTouched() {
        return alargeTouched;
    }

    public void setAlargeTouched(boolean alargeTouched) {
        this.alargeTouched = alargeTouched;
    }

    private boolean alargeTouched;

    private List<SectionRow<String,CalActivities>> activitiesList;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CalActViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView schedule;
        public TextView goal;
        private View view;
        private Button btnAddMinToHour;
        private int yBegin;
        private CalActOrgAdapter calActOrgAdapter;

        public CalActViewHolder(View v, final CalActOrgAdapter calActOrgAdapter) {

            super(v);
            this.calActOrgAdapter = calActOrgAdapter;
            this.schedule = (TextView)v.findViewById(R.id.txtScheduleCalAct);
            this.goal = (TextView)v.findViewById(R.id.txtGoalCalAct);
            this.btnAddMinToHour = v.findViewById(R.id.btnIncrementCalAct);
            RelativeLayout rlItemCalAct = v.findViewById(R.id.rl_item_cal_act);
            btnAddMinToHour.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int x=(int)event.getX();
                    int y=(int)event.getRawY();

                    int position[] = new int[2];
                    int height = view.getLayoutParams().height;
                    view.getLocationOnScreen(position);
                    int currentY = position[1];

                    //if((x - width <= 20 && x - width > 0) ||(width - x <= 20 && width - x > 0)){
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                //yBegin = y;
                                Log.d(TAG,"Button action down");
                                calActOrgAdapter.setBeginY(y);
                                calActOrgAdapter.setAlargeTouched(true);
                                calActOrgAdapter.setViewAlargin(view);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.d(TAG,"Button action move");
                                //Log.e(">>","width:"+width+" height:"+height+" x:"+x+" y:"+y);
                                //view.getLayoutParams().width = x;
                                /*Log.d("CalActOrg","Y = " + y + "CurrentY = " + currentY + " Height = "  + view.getLayoutParams().height);
                                view.getLayoutParams().height += y - yBegin;
                                Log.d("CalActOrg","Height = "  + view.getLayoutParams().height);
                                view.requestLayout();*/
                                break;
                            case MotionEvent.ACTION_UP:
                                Log.d(TAG,"Button action up");
                                //calActOrgAdapter.setAlargeTouched(false);
                                break;
                        }
                    //}
                    return false;
                }
            });
            this.view = v;
        }

        public void addData(CalActivities calActivities,int pos,Activity activity) {

            if(((pos + 1) % 2) == 0) {
                this.view.setBackgroundColor(activity.getResources().getColor(R.color.cal_act_1));
            } else {
                this.view.setBackgroundColor(activity.getResources().getColor(R.color.cal_act_2));
            }
            this.schedule.setText(calActivities.getSchedule());
            this.goal.setText(calActivities.getGoal());
        }

    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView day;

        public HeaderViewHolder(View v) {
            super(v);
            this.day = v.findViewById(R.id.txtDayHeaderCalAct);
        }

        public void addData(String day) {
            this.day.setText(day);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalActOrgAdapter(List<SectionRow<String,CalActivities>> myDataset,Activity activity) {
        this.activitiesList = myDataset;
        this.activity = activity;
        this.alargeTouched = false;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        if(viewType==IS_ROW) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cal_act, parent, false);
            return new CalActViewHolder(v,this);
        } else if ( viewType == IS_SECTION) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_header_cal_act, parent, false);
            return new HeaderViewHolder(v);
        }
        else
            return null;
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        SectionRow item = activitiesList.get(position);
        if(item.isRow()) {
            return IS_ROW;
        } else {
            return IS_SECTION;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SectionRow item = activitiesList.get(position);
        if(item.isRow()) {
            CalActViewHolder h = (CalActViewHolder) holder;
            h.addData((CalActivities) item.getRow(),position,activity);
        } else {
            HeaderViewHolder h = (HeaderViewHolder) holder;
            h.addData((String) item.getSection());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return activitiesList.size();
    }
}
