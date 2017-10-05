package com.example.lalo10.agenda;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by sebastian on 5/4/17.
 */
public class AppearFragment {

    public static void appearFragmentMA(MainActivity activity,Fragment newFragment, int position) {
        appearFragment(activity,newFragment,R.id.mainContent,position);
    }

    public static void appearFragment(MainActivity activity,Fragment newFragment, int idHolder,int position) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        // Despues remplazo el fragmento por actual por uno nuevo(fragment)
        ft.replace(idHolder, newFragment);
        // si quiero dejar que el usuario regrese al fragmento antiguo agrego esto, si no lo hago se destruye el fragmento
        ft.addToBackStack(null);
        //Animacion antes de que se ejecute la transaccion
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        activity.mDrawerList.setItemChecked(position, true);
        activity.setTitle(activity.mNavItems.get(position).mTitle);

        // Close the drawer
        activity.mDrawerLayout.closeDrawer(activity.mDrawerPane);
    }

}
