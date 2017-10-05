package com.example.lalo10.agenda;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by andel on 8/13/17.
 */

public class FragmentMeta extends Fragment {

    View rootView;
    Fechas startDate;
    Fechas endDate;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(
                R.layout.fragment_meta, container, false);
        setOnClickDatesHandlers(rootView);
        capitalizeFirstLetter(rootView);
        return rootView;
    }

    private void capitalizeFirstLetter(View view) {
        EditText txtMeta = (EditText)view.findViewById(R.id.editQuestionGoal);
        txtMeta.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    private DatePickerDialog.OnDateSetListener dpickerlistenerStart = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            startDate = new Fechas(i,i1,i2);
            TextView txtFecha = (TextView)rootView.findViewById(R.id.txtDateStart);
            Button btnEnd = (Button)rootView.findViewById(R.id.btnDateEnd);
            btnEnd.setEnabled(true);
            txtFecha.setText(startDate.toString());
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerlistenerEnd = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            endDate = new Fechas(i,i1,i2);
            TextView txtFecha = (TextView)rootView.findViewById(R.id.txtDateEnd);
            txtFecha.setText(endDate.toString());

        }
    };

    private void setOnClickDatesHandlers(View view) {
        Button btnStart = (Button)view.findViewById(R.id.btnDateBegin);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                if(startDate != null)
                    calendar = startDate.getCalendar();
                /*DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(),dpickerlistenerStart,startDate.getYear(), startDate.getMonth(), startDate.getDay());
                datePickerDialog1.show();*/
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setup(calendar,dpickerlistenerStart);
                newFragment.show(FragmentMeta.this.getFragmentManager(), "DatePicker");
            }
        });



        Button btnEnd = (Button)view.findViewById(R.id.btnDateEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calander = Calendar.getInstance();
                if(endDate != null)
                    calander = startDate.getMinCalendar();
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setup(calander,dpickerlistenerEnd);
                newFragment.show(FragmentMeta.this.getFragmentManager(), "DatePicker");
                /*DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(),dpickerlistenerEnd,endDate.getYear(), endDate.getMonth(), endDate.getDay());
                datePickerDialog1.show();*/
            }
        });
    }

}
