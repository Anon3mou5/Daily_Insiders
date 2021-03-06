package com.example.newsapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.newsapp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      final   ViewPager viewPager = findViewById(R.id.view_pager);

        TabLayout tabLayout;
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("BUSINESS_INSIDER"));
        tabLayout.addTab(tabLayout.newTab().setText("CNN"));
        tabLayout.addTab(tabLayout.newTab().setText("BUZZFEED"));
        tabLayout.addTab(tabLayout.newTab().setText("ESPN"));
        tabLayout.addTab(tabLayout.newTab().setText("BBC"));
        tabLayout.addTab(tabLayout.newTab().setText("POLITICO"));
        tabLayout.addTab(tabLayout.newTab().setText("BLOOMBERG"));
        tabLayout.addTab(tabLayout.newTab().setText("GOOGLE-NEWS"));

        tabLayout.addTab(tabLayout.newTab().setText("AL JAZEERA"));
        tabLayout.addTab(tabLayout.newTab().setText("AU-NEWS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}