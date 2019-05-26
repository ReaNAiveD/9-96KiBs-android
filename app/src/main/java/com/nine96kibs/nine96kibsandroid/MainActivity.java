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
import android.view.Menu;
import android.view.MenuItem;

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
                        floatingActionButton.setImageResource(R.drawable.ic_no_11);
                        break;
                    case 1:
                        floatingActionButton.setImageResource(R.drawable.ic_no_12);
                        break;
                    case 2:
                        floatingActionButton.setImageResource(R.drawable.ic_no_13);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setTag(R.id.view_pager_position, 0);
        floatingActionButton.setOnClickListener(v -> {
            String text = (int) v.getTag(R.id.view_pager_position) + getResources().getString(R.string.warning);
            Snackbar.make(v, text, Snackbar.LENGTH_SHORT).setAction("OK", null).show();
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.id_no_01:
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
            String text = menuItem.getItemId() + getResources().getString(R.string.warning);
            Snackbar.make(viewPager, text, Snackbar.LENGTH_SHORT).setAction("OK", null).show();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        drawerLayout = findViewById(R.id.activity_main);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_body_view_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_no_09) {
            Snackbar.make(viewPager, R.id.id_no_09 + getResources().getString(R.string.warning), Snackbar.LENGTH_SHORT).setAction("OK", null).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
