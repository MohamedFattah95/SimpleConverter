package com.example.mohamed.simpleconverter.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mohamed.simpleconverter.Fragments.AreaActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.DigitalStorageActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.LengthActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.MainActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.NumberSystemActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.TemperatureActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.TimeActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.VolumeActivityFragment;
import com.example.mohamed.simpleconverter.Fragments.WeightActivityFragment;
import com.example.mohamed.simpleconverter.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectTheme();
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            MainActivityFragment mainActivityFragment = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.flMain, mainActivityFragment).commit();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        selectNavItemColor();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (itemId) {
            case R.id.item_home:
                transaction.replace(R.id.flMain, new MainActivityFragment(), "home").commit();
                toolbar.setTitle(R.string.app_name);
                break;
            case R.id.item_temp:
                transaction.replace(R.id.flMain, new TemperatureActivityFragment()).commit();
                toolbar.setTitle(R.string.temp);
                break;
            case R.id.item_length:
                transaction.replace(R.id.flMain, new LengthActivityFragment()).commit();
                toolbar.setTitle(R.string.length);
                break;
            case R.id.item_weight:
                transaction.replace(R.id.flMain, new WeightActivityFragment()).commit();
                toolbar.setTitle(R.string.weight);
                break;
            case R.id.item_volume:
                transaction.replace(R.id.flMain, new VolumeActivityFragment()).commit();
                toolbar.setTitle(R.string.volume);
                break;
            case R.id.item_time:
                transaction.replace(R.id.flMain, new TimeActivityFragment()).commit();
                toolbar.setTitle(R.string.time);
                break;
            case R.id.item_area:
                transaction.replace(R.id.flMain, new AreaActivityFragment()).commit();
                toolbar.setTitle(R.string.area);
                break;
            case R.id.item_storage:
                transaction.replace(R.id.flMain, new DigitalStorageActivityFragment()).commit();
                toolbar.setTitle(R.string.storage);
                break;
            case R.id.item_number:
                transaction.replace(R.id.flMain, new NumberSystemActivityFragment()).commit();
                toolbar.setTitle(R.string.number);
                break;
            case R.id.item_settings:
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                break;
        }

        hideDrawer();
        return true;
    }

    private void showDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            hideDrawer();
        else {
            Fragment currentFragment = this.getSupportFragmentManager().findFragmentByTag("home");
            if (currentFragment instanceof MainActivityFragment)
                super.onBackPressed();
            else {
                getSupportFragmentManager().beginTransaction().replace(R.id.flMain, new MainActivityFragment(), "home").commit();
                toolbar.setTitle(R.string.app_name);
            }
        }
    }

    public void selectTheme() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = prefs.getString(getString(R.string.pref_appear_key),
                getString(R.string.pref_appear_light));

        switch (theme) {
            case "light":
            default:
                setTheme(R.style.AppTheme);
                break;
            case "dark":
                setTheme(R.style.AppThemeDark);
                break;
        }
    }

    public void selectNavItemColor() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = prefs.getString(getString(R.string.pref_appear_key),
                getString(R.string.pref_appear_light));

        switch (theme) {
            case "light":
            default:
                navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
                navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                break;
            case "dark":
                navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.navItemColorDark)));
                navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.navItemTintColorDark)));
                break;
        }
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flMain, fragment).commit();
    }


}
