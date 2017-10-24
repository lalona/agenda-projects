package com.example.lalo10.agenda.NewProyect;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.lalo10.agenda.Database.Proyectos.GoalsDBDAO;
import com.example.lalo10.agenda.Database.Proyectos.ProyectAccessDatabase;
import com.example.lalo10.agenda.Database.Proyectos.ProyectDBHelper;
import com.example.lalo10.agenda.Dialogos.DialogsHelper;
import com.example.lalo10.agenda.Dialogos.DialogsParameters;
import com.example.lalo10.agenda.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lalo10 on 10/5/17.
 * Design pattern: singleton
 */

public class NewProjectData {

    private static NewProjectData onlyinstance;
    private Activity context;



    public List<String> getColoredItems() {
        if(coloredItems == null)
            coloredItems = new ArrayList<>();
        return coloredItems;
    }

    public void setColoredItems(List<String> coloredItems) {
        this.coloredItems = coloredItems;
    }

    private List<String> coloredItems;

    FragmentHours fragmentHours;

    public enum PHASE {
        GOAL,DATES,DAYS,HOURS
    }


    private NewProjectData() {

    }

    public static NewProjectData getInstance(Activity context) {
        if(onlyinstance == null) {
            onlyinstance = new NewProjectData();
        }
        onlyinstance.context = context;
        return onlyinstance;
    }

    String goal;

    public String getGoal() {
        return goal;
    }

    public Fechas getStart() {
        return start;
    }

    public Fechas getEnd() {
        return end;
    }

    public String getEndString() {
        return end.getStringFormat();
    }

    public String getStartString() {
        return start.getStringFormat();
    }



    Fechas start;
    Fechas end;
    List<DayId> daysSelected;

    public void start() {

    }

    public void setDaysSelected(List<DayId> daysSelected) {
        this.daysSelected = daysSelected;
        sendDaysToHours();
        ifFinishAskIfSave();
    }

    public void setDaysSelectedAvoidStackOverFlow(List<DayId> daysSelected) {
        this.daysSelected = daysSelected;
        ifFinishAskIfSave();
    }

    private void ifFinishAskIfSave() {
        if(checkIfCompleted())
            DialogsHelper.showQuestionDialog(context, new DialogsParameters(context) {
                @Override
                public void yesCall() {

                }

                @Override
                public void noCall() {

                }

                @Override
                public int getMessage() {
                    return R.string.question_want_to_save_project;
                }

                @Override
                public int getTitle() {
                    return R.string.project_data_filled;
                }
            });
    }

    private boolean checkIfCompleted() {
        if(goal == null)
            return false;
        if(daysSelected == null)
            return false;
        if(start == null || end == null)
            return false;
        if(daysSelected.size() == 0)
            return false;
        for(DayId d : daysSelected) {
            if(d.fromHour == null || d.toHour == null)
                return false;
        }
        return true; // The user provided all the necessary data

    }

    public void subscribe(PHASE subscribe, Fragment fragment) {
        switch (subscribe) {
            case GOAL:
                break;
            case DATES:
                break;
            case DAYS:
                break;
            case HOURS:
                fragmentHours = (FragmentHours) fragment;
                break;
        }
    }



    public List<DayId> getDaysSelected() {
        if(daysSelected == null) {
            daysSelected = new ArrayList<>();
        }
        ifFinishAskIfSave();
        return daysSelected;
    }

    public String getDaysSelectedBits() {
        StringBuffer bits = new StringBuffer();
        DayId[] allDays = DayId.getArrayDays(context);
        for(int i = 0; i < allDays.length; i++) {
            if(daysSelected.contains(allDays[i]))
                bits.append(1); // Si el dia esta lo voy a activar
            else
                bits.append(0); // Si no es 0
        }
        return bits.toString();
    }

    private void sendDaysToHours() {
        if(fragmentHours != null)
            fragmentHours.setDaysSelected(daysSelected);
    }

    public void setGoal(String goal) {
        this.goal = goal;
        ifFinishAskIfSave();
    }

    public void setStart(Fechas start) {
        this.start = start;
        ifFinishAskIfSave();
    }

    public void setEnd(Fechas end) {
        this.end = end;
        ifFinishAskIfSave();
    }

}
