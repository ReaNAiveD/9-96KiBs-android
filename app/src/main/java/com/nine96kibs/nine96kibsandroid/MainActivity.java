package com.nine96kibs.nine96kibsandroid;

import android.content.SharedPreferences;
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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nine96kibs.nine96kibsandroid.fragment.ViewPagerFragmentPagerAdapter;
import com.nine96kibs.nine96kibsandroid.recite.ReciteTask;
import com.nine96kibs.nine96kibsandroid.recite.ReciteTaskAdapter;
import com.nine96kibs.nine96kibsandroid.vo.TaskInfoVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton floatingActionButton;
    NavigationView navigationView;
    Toolbar toolbar;
    ViewPager viewPager;
    RecyclerView.Adapter reciteAdapter;
    public static int userId;
    public static int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", 0);

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
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                TextView textView = findViewById(R.id.textView);
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                textView.setText(sharedPreferences.getString("name", ""));
            }
        };
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
