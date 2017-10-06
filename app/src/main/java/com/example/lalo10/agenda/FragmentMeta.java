package com.example.lalo10.agenda;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(
                R.layout.fragment_meta, container, false);
        capitalizeFirstLetter(rootView);
        return rootView;
    }

    private void capitalizeFirstLetter(View view) {
        EditText txtMeta = (EditText)view.findViewById(R.id.editQuestionGoal);
        txtMeta.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    private void addListenerToContinue(View view) {
        Button btn = (Button) view.findViewById(R.id.btnContinue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
