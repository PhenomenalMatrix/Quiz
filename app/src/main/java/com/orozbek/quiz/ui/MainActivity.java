package com.orozbek.quiz.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.orozbek.quiz.QuizApp;
import com.orozbek.quiz.R;
import com.orozbek.quiz.data.IQuizApiClient;
import com.orozbek.quiz.model.Category;
import com.orozbek.quiz.model.Question;
import com.orozbek.quiz.ui.adapter.MainViewPagerAdapter;
import com.orozbek.quiz.ui.customsView.CustomViewPager;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private CustomViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        viewPager = findViewById(R.id.main_view_pager);


        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_nav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.history_nav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.settings_nav:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });


    }

}