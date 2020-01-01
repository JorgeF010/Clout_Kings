package com.example.cloutkings;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


/**
 * Version: 1.0 - Underdevelopment, Uses JSoup, AsyncTask, Classes, Dataclasses and more.
 * Author: JorgeF010 - Github, jorge-f - Linkedin
 * Description: This class creates the bottom navigation UI from the menu xml -
 * it then creates a listener to switch between fragments. But when it is initialized -
 * it sets the fragment_home as the first page. The rest is take care of by the HomeFragment class.
 */
public class MainActivity extends AppCompatActivity {

    private Fragment home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        // Nav Click Listener - calls method
        navView.setOnNavigationItemSelectedListener(navListener);
        // Replaces right away the home page with the HomeFragment
        this.home = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, home).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_categories:
                    selectedFragment = new CategoriesFragment();
                    break;
                case R.id.navigation_trending:
                    selectedFragment = new TrendingFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };


}
