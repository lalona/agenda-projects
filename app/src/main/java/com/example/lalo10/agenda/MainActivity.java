package com.example.lalo10.agenda;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavItems.add(new NavItem(getString(R.string.my_moment), getString(R.string.what_you_should_b_d) , R.drawable.ic_time, new ListMenuClick() {
            @Override
            public void action(int position) {
                selectItemFromDrawer(position);
            }
        }));

        mNavItems.add(new NavItem(getString(R.string.new_day), getString(R.string.plan_a_new_day), R.drawable.ic_pencil, new ListMenuClick() {
            @Override
            public void action(int position) {
                selectItemFromDrawer(position);
            }
        }));

        mNavItems.add(new NavItem(getString(R.string.new_project), getString(R.string.accomplish_something), R.drawable.ic_ship, new ListMenuClick() {
            @Override
            public void action(int position) {
                //FragmentNewProyect fragmentNewProyect = new FragmentNewProyect();
                //AppearFragment.appearFragmentMA(MainActivity.this,fragmentNewProyect,position);


                //final Intent intent = new Intent(MainActivity.this, FragmentNewProyect.class);
                final Intent intent = new Intent(MainActivity.this, FragmentNewProyectInk.class);
                intent.putExtras(new Bundle());
                //intent.putExtra(BLE_ADDRESS, ble_address);
                startActivity(intent);
            }
        }));
        mNavItems.add(new NavItem(getString(R.string.history), getString(R.string.look_at_your_progress), R.drawable.ic_history, new ListMenuClick() {
            @Override
            public void action(int position) {
                selectItemFromDrawer(position);
            }
        }));
        mNavItems.add(new NavItem(getString(R.string.help), getString(R.string.how_to_improve_your_life), R.drawable.ic_battery, new ListMenuClick() {
            @Override
            public void action(int position) {
                selectItemFromDrawer(position);
            }
        }));

        // put the hamburguer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selectItemFromDrawer(position);
                //selectItemFromDrawer(position);
                NavItem navItem = (NavItem) parent.getItemAtPosition(position);
                navItem.doAction(position);

            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        //Drawable d=getResources().getDrawable(R.drawable.background_actionbar);
        //getSupportActionBar().setBackgroundDrawable(d);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /*
* Called when a particular item from the navigation drawer
* is selected.
* */
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new PreferenceFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setIcon(R.drawable.ic_bookmark,R.id.avatar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.findItem(R.id.hamburguer).setVisible(false);
        return true;
    }

    private abstract class ListMenuClick {
        public abstract void action(int position);
    }

    /*
    // Called when invalidateOptionsMenu() is invoked
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerPane);
        menu.findItem(R.id.hamburguer).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setIcon(int resourceDrawable, int resourceObject) {
        ImageView i = (ImageView) findViewById(resourceObject);
        Drawable drawable;

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            drawable = getResources().getDrawable(resourceDrawable, getTheme());
        } else {
            drawable = VectorDrawableCompat.create(getResources(), resourceDrawable, getTheme());
        }
        if(i != null)
            i.setImageDrawable(drawable);
    }

    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;
        ListMenuClick click;

        public NavItem(String title, String subtitle, int icon,ListMenuClick click) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
            this.click = click;
        }

        public void doAction(int position) {
            this.click.action(position);
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            //iconView.setImageResource(mNavItems.get(position).mIcon);
            setIcon(mNavItems.get(position).mIcon,iconView);

            return view;
        }

        private void setIcon(int resourceDrawable, ImageView i) {
            //ImageView i = (ImageView) findViewById(resourceObject);
            Drawable drawable;

            if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                drawable = getResources().getDrawable(resourceDrawable, getTheme());
            } else {
                drawable = VectorDrawableCompat.create(getResources(), resourceDrawable, getTheme());
            }
            if(i != null)
                i.setImageDrawable(drawable);
        }
    }

}
