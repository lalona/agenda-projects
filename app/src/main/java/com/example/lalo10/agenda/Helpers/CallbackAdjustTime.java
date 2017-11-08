package com.example.lalo10.agenda.Helpers;

import android.view.View;

import com.example.lalo10.agenda.List_Adapters.SectionRow;
import com.example.lalo10.agenda.NewProyect.CalActivities;

import java.util.List;

/**
 * Created by lalo10 on 11/8/17.
 */
public interface CallbackAdjustTime {
    void adjustHour(int min, List<SectionRow<String,CalActivities>> activitiesList, int pos, AdjustmentHelper.Direction direction,View viewNeigh);
}
