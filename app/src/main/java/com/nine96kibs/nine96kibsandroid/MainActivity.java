package com.nine96kibs.nine96kibsandroid;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nine96kibs.nine96kibsandroid.fragment.ViewPagerFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton floatingActionButton;
    NavigationView navigationView;
    Toolbar toolbar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                floatingActionButton.setTag(R.id.view_pager_position, position);
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(position).getItemId());
                switch (position) {
                    case 0:
                        toolbar.setTitle("选择任务");
                        floatingActionButton.setAlpha(0f);
                        break;
                    case 1:
                        toolbar.setTitle("细读原句");
                        floatingActionButton.setAlpha(1f);
                        break;
                    case 2:
                        toolbar.setTitle("收藏（6）");
                        floatingActionButton.setAlpha(0f);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setTag(R.id.view_pager_position, 0);
        floatingActionButton.setAlpha(0f);
        floatingActionButton.setOnClickListener(v -> {
            if ((int) floatingActionButton.getTag(R.id.view_pager_position) == 1) {
                Snackbar.make(viewPager, getResources().getString(R.string.warning), Snackbar.LENGTH_SHORT).setAction("OK", null).show();

            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.recite:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.id_no_02:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.id_no_03:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isChecked()) {
                menuItem.setChecked(false);
            } else {
                menuItem.setChecked(true);
            }
            return true;
        });

        drawerLayout = findViewById(R.id.activity_main);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
