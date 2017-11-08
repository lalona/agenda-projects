package com.example.lalo10.agenda.List_Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lalo10.agenda.Helpers.AdjustmentHelper;
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


    private static final String TAG = CalActOrgAdapter.class.getSimpleName();

    private List<SectionRow<String,CalActivities>> activitiesList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalActOrgAdapter(List<SectionRow<String,CalActivities>> myDataset,Activity activity) {
        this.activitiesList = myDataset;
        this.activity = activity;
        AdjustmentHelper.setActivitiesList(myDataset);
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

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        if(viewType==IS_ROW) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cal_act, parent, false);
            return new CalActViewHolder(v);
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
