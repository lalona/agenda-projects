package com.example.lalo10.agenda;

/**
 * Created by lalo10 on 10/5/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.common.collect.Lists;

import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;


public class FragmentNewProyectInk extends ActionBarActivity {

    ScrollerViewPager viewPager;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_proyect_ink);

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        actionBar = getSupportActionBar();

        actionBar.setTitle(R.string.new_project);
        actionBar.setDisplayHomeAsUpEnabled(true);

        PagerModelManager manager = new PagerModelManager();
        //manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
        Fragment fragmentMeta = new FragmentMeta();
        manager.addFragment(fragmentMeta,getResources().getString(R.string.meta));
        Fragment fragmentDates = new FragmentProyectDays();
        manager.addFragment(fragmentDates,getResources().getString(R.string.dates));

        Fragment fragmentListDays = new FragmentListDays();
        manager.addFragment(fragmentListDays,getResources().getString(R.string.days));
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager);

    }

    /*private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4");
    }

    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4);
    }*/


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}