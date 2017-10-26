package com.example.lalo10.agenda.NewProyect;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.example.lalo10.agenda.Dialogos.DialogsAlert;
import com.example.lalo10.agenda.Dialogos.DialogsHelper;
import com.example.lalo10.agenda.Dialogos.DialogsParameters;
import com.example.lalo10.agenda.R;

/**
 * Created by lalo10 on 10/26/17.
 */

public class CommonListeners {
    public boolean listenerForSave(final Activity context,View view) {
        FloatingActionButton btnSave = (FloatingActionButton)view.findViewById(R.id.btnSaveProyect);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewProjectData newProjectData = NewProjectData.getInstance(context);
                if(!newProjectData.trySave())
                    DialogsHelper.showAlert(context, new DialogsAlert() {
                        @Override
                        public int getMessage() {
                            return R.string.complete_all_needed_fields;
                        }

                        @Override
                        public int getTitle() {
                            return R.string.canot_save;
                        }

                        @Override
                        public void actionAfterOk() {
                        }
                    });
            }
        });
        btnSave.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.floating_button)));
        return true;
    }

}
