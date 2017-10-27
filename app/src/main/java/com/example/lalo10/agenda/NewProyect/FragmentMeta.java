package com.example.lalo10.agenda.NewProyect;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lalo10.agenda.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by andel on 8/13/17.
 */

public class FragmentMeta extends Fragment {

    View rootView;
    NewProjectData newProjectData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newProjectData = NewProjectData.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(
                R.layout.fragment_meta, container, false);
        capitalizeFirstLetter(rootView);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new CommonListeners().listenerForSave(getActivity(),rootView);
    }

    private void capitalizeFirstLetter(View view) {
        EditText txtMeta = (EditText)view.findViewById(R.id.editQuestionGoal);

        /*txtMeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //newProjectData.setGoal(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                newProjectData.setGoal(charSequence.toString());
            }
        });*/
        txtMeta.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if(event == null) {
                                newProjectData.setGoal(v.getText().toString());
                                return false; // consume.
                            }
                            else if (!event.isShiftPressed()) {
                                // the user is done typing.
                                newProjectData.setGoal(v.getText().toString());
                                return false; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });
        txtMeta.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }



}
