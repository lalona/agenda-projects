package com.example.lalo10.agenda.NewProyect;

import android.icu.text.SelectFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.lalo10.agenda.List_Adapters.CalActOrgAdapter;
import com.example.lalo10.agenda.List_Adapters.SectionRow;
import com.example.lalo10.agenda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalo10 on 10/27/17.
 */

public class FragmentCalendarAct extends Fragment{
    private RecyclerView mRecyclerView;
    private CalActOrgAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    View rootView;
    private int beginY;
    private int beginDiff;
    private static final String TAG = FragmentCalendarAct.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(
                R.layout.fragment_calendar_days_selected, container, false);
        setRetainInstance(true);
        mRecyclerView = rootView.findViewById(R.id.recycler_view_calendar_days);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CalActOrgAdapter(getDumbData(),getActivity());
        mRecyclerView.setAdapter(mAdapter);
        interceptRecycleView();
        return rootView;
    }

    private void interceptRecycleView() {

        RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                /*if (e.getAction() == MotionEvent.ACTION_DOWN && rv.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
                    //Log.d(TAG, "onInterceptTouchEvent: click performed");
                    //rv.findChildViewUnder(e.getX(), e.getY()).performClick();
                    if(mAdapter.isAlargeTouched())
                        Log.d("FragmentCalendarAct","Is alarging");
                    else
                        Log.d("FragmentCalendarAct","Naaaa");
                    return true;
                }*/
                if(mAdapter.isAlargeTouched()) {
                    Log.d("FragmentCalendarAct","Is alarging");
                    int y = (int) e.getRawY();
                    switch (e.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            beginDiff = mAdapter.getBeginY() - y;
                            Log.d(TAG,"Recycler action move " + beginDiff);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d(TAG,"Recycler action move");
                            //Log.e(">>","width:"+width+" height:"+height+" x:"+x+" y:"+y);
                            //view.getLayoutParams().width = x;
                            mAdapter.alargeView(y + 1000);
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d(TAG,"Recycler action up");
                            mAdapter.setAlargeTouched(false);
                            break;
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        };
        //mRecyclerView.addOnItemTouchListener(mOnItemTouchListener);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                if(mAdapter.isAlargeTouched()) {
                    Log.d("FragmentCalendarAct","Is alarging");
                    int y = (int) e.getRawY();
                    switch (e.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            beginDiff = mAdapter.getBeginY() - y;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d(TAG,"Recycler action move");
                            //Log.e(">>","width:"+width+" height:"+height+" x:"+x+" y:"+y);
                            //view.getLayoutParams().width = x;
                            mAdapter.alargeView(y + beginDiff);
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d(TAG,"Recycler action up");
                            mAdapter.setAlargeTouched(false);
                            break;
                    }
                    return true;
                }
                return false;
            }
        });
    }


    private List<SectionRow<String,CalActivities>> getDumbData() {
        List<SectionRow<String,CalActivities>> listDumbData = new ArrayList<>();
        SectionRow<String,CalActivities> sectionRowHelper = new SectionRow<>();

        SectionRow<String,CalActivities> section = sectionRowHelper.makeSection("Lunes");
        listDumbData.add(section);
        for(int i = 0; i < 5; i++) {
            SectionRow<String,CalActivities> row = sectionRowHelper.makeRow(new CalActivities());
            listDumbData.add(row);
        }
        SectionRow<String,CalActivities> section2 = sectionRowHelper.makeSection("Miercoles");
        listDumbData.add(section2);
        for(int i = 0; i < 3; i++) {
            SectionRow<String,CalActivities> row = sectionRowHelper.makeRow(new CalActivities());
            listDumbData.add(row);
        }
        SectionRow<String,CalActivities> section3 = sectionRowHelper.makeSection("Viernes");
        listDumbData.add(section3);
        for(int i = 0; i < 6; i++) {
            SectionRow<String,CalActivities> row = sectionRowHelper.makeRow(new CalActivities());
            listDumbData.add(row);
        }
        return listDumbData;

    }

}
