package com.example.lalo10.agenda.NewProyect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.lalo10.agenda.List_Adapters.AdapterForSelectItem;
import com.example.lalo10.agenda.List_Adapters.AdapterHourFromTo;
import com.example.lalo10.agenda.R;

import java.util.List;

/**
 * Created by lalo10 on 10/6/17.
 */

public class FragmentHours extends ListFragment {

    View rootView;
    ListView listView;
    AdapterHourFromTo adapterHourFromTo;
    List<DayId> daysSelected;
    NewProjectData newProjectData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newProjectData = NewProjectData.getInstance(getActivity());
        daysSelected = newProjectData.getDaysSelected();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_hours_day, container, false);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getListView();
        if(listView.getAdapter() == null) {
            adapterHourFromTo = new AdapterHourFromTo(this,getActivity(),R.layout.item_day_hour,daysSelected);
            listView.setAdapter(adapterHourFromTo);
        }
        //registerForContextMenu(listView);
    }

}
