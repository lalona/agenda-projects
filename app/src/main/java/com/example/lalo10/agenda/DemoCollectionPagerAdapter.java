package com.example.lalo10.agenda;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lalo10 on 8/11/17.
 */

class DemoCollectionPagerAdapter extends FragmentPagerAdapter {
    // Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
    public DemoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    final int META = 0;
    final int FECHAS = 1;
    final int CALENDARIO = 2;
    final int PLAN_A_DAY = 3;
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case META:
                Fragment fragmentMeta = new FragmentMeta();
                return fragmentMeta;
            case FECHAS:
                //return;
            case CALENDARIO:
                //return;
            case PLAN_A_DAY:
                //return;
        }
        Fragment fragment = new DemoObjectFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

    // Instances of this class are fragments representing a single
// object in our collection.
    public static class DemoObjectFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.

            View rootView = inflater.inflate(
                    R.layout.fragment_preference, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(R.id.text)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }

}


