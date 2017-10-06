package com.example.lalo10.agenda.NewProyect;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lalo10.agenda.R;

import java.util.Calendar;

/**
 * Created by lalo10 on 10/5/17.
 */

public class FragmentProyectDays extends Fragment {
    View rootView;
    Fechas startDate;
    Fechas endDate;
    NewProjectData newProjectData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newProjectData = NewProjectData.getInstance(getActivity());
        this.startDate = newProjectData.getStart();
        this.endDate = newProjectData.getEnd();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(
                R.layout.fragment_proyect_dates, container, false);
        setOnClickDatesHandlers(rootView);
        setRetainInstance(true);
        return rootView;
    }


    private DatePickerDialog.OnDateSetListener dpickerlistenerStart = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            startDate = new Fechas(i,i1,i2);
            newProjectData.setStart(startDate);
            setStartDateText();
        }
    };

    private void setStartDateText() {
        if(startDate == null)
            return;
        TextView txtFecha = (TextView)rootView.findViewById(R.id.txtDateStart);
        txtFecha.setText(startDate.toString());
    }

    private void setEndDateText() {
        if(endDate == null)
            return;
        TextView txtFecha = (TextView)rootView.findViewById(R.id.txtDateEnd);
        txtFecha.setText(endDate.toString());
    }

    private DatePickerDialog.OnDateSetListener dpickerlistenerEnd = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            endDate = new Fechas(i,i1,i2);
            newProjectData.setEnd(endDate);
            setEndDateText();

        }
    };

    private void setOnClickDatesHandlers(View view) {
        ImageButton btnStart = (ImageButton) view.findViewById(R.id.btnDateBegin);
        setStartDateText();
        setEndDateText();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Calendar calendar = Calendar.getInstance();
                if(startDate != null)
                    calendar = startDate.getCalendar();
                /*DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(),dpickerlistenerStart,startDate.getYear(), startDate.getMonth(), startDate.getDay());
                datePickerDialog1.show();*/
                /*DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setup(calendar,dpickerlistenerStart);
                newFragment.show(FragmentMeta.this.getFragmentManager(), "DatePicker");*/
                Calendar calendar = Calendar.getInstance(); // today
                Calendar start = Calendar.getInstance();
                if(startDate != null)
                    start = startDate.getCalendar();
                Calendar endCalendar = null;
                if(endDate != null)
                    endCalendar = endDate.getCalendar();
                appearDatePickerWithLimit(calendar,start,endCalendar,dpickerlistenerStart);
                ImageButton btnEnd = (ImageButton)rootView.findViewById(R.id.btnDateEnd);
                btnEnd.setEnabled(true);

            }
        });



        ImageButton btnEnd = (ImageButton)view.findViewById(R.id.btnDateEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startDate == null)
                    return;
                /*
                Calendar calander = Calendar.getInstance();
                if(endDate != null)
                    calander = startDate.getMinCalendar();
                /*DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setup(calander,dpickerlistenerEnd);
                newFragment.show(FragmentProyectDays.this.getFragmentManager(), "DatePicker");*/
                /*DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(),dpickerlistenerEnd,endDate.getYear(), endDate.getMonth(), endDate.getDay());
                datePickerDialog1.getDatePicker().setMinDate(calander.getTimeInMillis());
                datePickerDialog1.show();*/
                Calendar calendar = startDate.getMinCalendar();
                Calendar start = startDate.getMinCalendar();
                if(endDate != null)
                    start = endDate.getMaxCalendar();
                appearDatePickerWithLimit(calendar,start,null,dpickerlistenerEnd);
            }
        });
    }

    // 1 una la voy a usar con start para iniciar en el dia actual y no permitir ir mas abajo
    // limite de start siempre es hoy
    //limite de end siempre es el inicio de start
    private void appearDatePickerWithLimit(Calendar limitMin,Calendar start,Calendar limitMax,DatePickerDialog.OnDateSetListener listener) {
        //Calendar calander = Calendar.getInstance();
        int year = start.get(Calendar.YEAR);
        int month = start.get(Calendar.MONTH);
        int day = start.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(),listener,year, month, day);
        datePickerDialog1.getDatePicker().setMinDate(limitMin.getTimeInMillis());
        if(limitMax != null) {
            datePickerDialog1.getDatePicker().setMaxDate(limitMax.getTimeInMillis());
        }
        datePickerDialog1.show();
    }

}
