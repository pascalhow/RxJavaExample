package com.pascalhow.navigationdrawerbaseapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pascalhow.navigationdrawerbaseapp.mainfragment.MainFragment;
import com.pascalhow.navigationdrawerbaseapp.newfragment.NewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String FRAGMENT_MAIN = "main";
    private static final String FRAGMENT_NEW = "new";


    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        setOnBackStackListener();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(new MainFragment(), FRAGMENT_MAIN);
    }

    /**
     * Replaces or adds a new fragment on top of the current fragment
     * @param fragment The new fragment
     * @param tag A tag relating to the new fragment
     */
    public void loadFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (tag) {

            //  Main fragment is the first fragment to be displayed so we don't addToBackStack()
            case FRAGMENT_MAIN:
                fragmentManager.beginTransaction()
                        .replace(R.id.base_fragment, fragment, tag)
                        .commit();
                break;

            case FRAGMENT_NEW:
                fragmentManager.beginTransaction()
                        .add(R.id.base_fragment, fragment, tag)
                        .addToBackStack(FRAGMENT_NEW)
                        .commitAllowingStateLoss();
            default:
                break;
        }
    }

    public void showFloatingActionButton() {
        fab.show();
    }

    public void hideFloatingActionButton() {
        fab.hide();
    }

    /**
     * Handles backStackListener when user navigates between fragments
     */
    private void setOnBackStackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(
                () -> {
                    // Update your UI here.
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.base_fragment);
                    if (fragment != null) {
                        updateFragmentTitle(fragment);
                    }
                });
    }

    /**
     * Handle screen display when navigating between fragment
     * @param fragment The current fragment
     */
    private void updateFragmentTitle(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(MainFragment.class.getName())) {
            setTitle(getResources().getString(R.string.main_screen_fragment_title));
            showFloatingActionButton();
        } else if (fragClassName.equals(NewFragment.class.getName())) {
            setTitle(getResources().getString(R.string.new_screen_fragment_title));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            loadFragment(new MainFragment(), FRAGMENT_MAIN);
        } else if (id == R.id.nav_gallery) {
            loadFragment(new NewFragment(), FRAGMENT_NEW);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
