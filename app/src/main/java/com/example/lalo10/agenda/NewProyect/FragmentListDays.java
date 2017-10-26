package com.example.lalo10.agenda.NewProyect;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.lalo10.agenda.List_Adapters.AdapterForSelectItem;
import com.example.lalo10.agenda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalo10 on 10/5/17.
 */

public class FragmentListDays extends ListFragment {

    /* Guardo la actividad en la que se agrega este fragmento */
    private static ListView listView;
    private List<String> coloredItems;
    //private String banderaModo;
    private Button btnSAll;
    private static final String TAG = FragmentListDays.class.getSimpleName();
    AdapterForSelectItem adapterForSelectItem;

    //AdapterForSelectItemGraph adapterGraph;
    ViewGroup rootView;
    //AppearList appearList;
    private boolean adapterIsNotSet;

    private boolean selecciona;

    private DayId[] days;
    private List<DayId> daysSelected;

    NewProjectData newProjectData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterIsNotSet = false;
        //appearList = null;
        selecciona = true;
        days = DayId.getArrayDays(getActivity());
        newProjectData = NewProjectData.getInstance(getActivity());
        //newProjectData.setDaysSelected(daysSelected);
        daysSelected = newProjectData.getDaysSelected();
        coloredItems = newProjectData.getColoredItems();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_days_week_selection, container, false);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getListView();
        btnSAll = (Button) getView().findViewById(R.id.btnSelDesTodo);
        AddListenerButtons();
        if(listView.getAdapter() == null) {
            adapterForSelectItem = new AdapterForSelectItem(getActivity(),R.layout.list_item_name,DayId.getArrayDays(getActivity()));
            listView.setAdapter(adapterForSelectItem);
            if (!coloredItems.isEmpty())
                adapterForSelectItem.setColoredItems(coloredItems);
        }
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //para que pueda escoger varios
        listView.setItemsCanFocus(true);
        registerForContextMenu(listView);
        new CommonListeners().listenerForSave(getActivity(),rootView);
    }



    // Handle Item click event
    public void onListItemClick(ListView l, View view, int position, long id){
        String currentName = days[position].nombre;
        if (coloredItems.contains(currentName)) {
            //remove position from coloredItems
            view.setBackgroundColor(Color.parseColor("#00000000"));
            //getGroups.remove(listView,position);
            daysSelected.remove(listView.getItemAtPosition(position));
            coloredItems.remove(currentName);

        }
        else {
            //add position to coloredItems
            view.setBackgroundColor(Color.parseColor("#D0BEBEBE"));
            coloredItems.add(currentName);
            //getGroups.add(position);
            daysSelected.add(((DayId)listView.getItemAtPosition(position)));

        }
        this.newProjectData.setDaysSelected(daysSelected);
        newProjectData.setColoredItems(coloredItems);
        adapterForSelectItem.setColoredItems(coloredItems);
    }

    private void AddListenerButtons() {
        btnSAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selecciona) {
                    seleccionaTodo();
                    selecciona = false;
                    btnSAll.setText("-" + getResources().getString(R.string.all));
                }
                else {
                    deseleccionaTodo();
                    selecciona = true;
                    btnSAll.setText("+" + getResources().getString(R.string.all));
                }
                newProjectData.setDaysSelected(daysSelected);
                newProjectData.setColoredItems(coloredItems);
            }
        });
    }

    private void deseleccionaTodo() {
        daysSelected.clear();
        //getGroups.removeAll();
        //getGroups.getAdapter().getColoredItems().clear();
        adapterForSelectItem.clearColoredItems();
        View viewChild;
        for(int j = 0; j <= listView.getLastVisiblePosition(); j++ ) {
            //Log.d(TAG,"La cuenta va en: " + j + " de: " + listView.getCount());
            viewChild = listView.getChildAt(j);
            if(viewChild != null)
                viewChild.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }

    private void seleccionaTodo() {
        //Selecciono todos los grupos
        //Log.d(TAG,"First position: " + listView.getFirstVisiblePosition());
        //Log.d(TAG,"Last position: " + listView.getLastVisiblePosition());
        String tempName;
        String colorName;
        daysSelected.clear();
        for(int j = 0; j < listView.getCount(); j++ ) {
            //tempName = nameGroups.get(j).toString();
            tempName = days[j].nombre;
            if (!coloredItems.contains(tempName)) {
                //Log.d(TAG,"La cuenta va en: " + j + " de: " + listView.getCount());
                if(j <= (listView.getLastVisiblePosition() - listView.getFirstVisiblePosition())) {
                    //Log.d(TAG,"La cuenta para marcar el background: " + j + " de: " + listView.getCount());
                    if(listView.getChildAt(j) != null)
                        listView.getChildAt(j).setBackgroundColor(Color.parseColor("#D0BEBEBE"));
                }
                coloredItems.add(tempName);
                //getGroups.add(j);
                daysSelected.add(days[j]);
            }
        }

        adapterForSelectItem.setColoredItems(coloredItems);
    }


}
