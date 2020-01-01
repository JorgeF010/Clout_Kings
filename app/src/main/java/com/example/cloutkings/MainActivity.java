package com.example.cloutkings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cloutkings.ui.ProfileAdapter;
import com.example.cloutkings.ui.Score;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private Fragment home;
    // Request Button
    private Button request;
    // upVote Button
    private ImageButton upVote;
    // downVote Button
    private ImageButton downVote;

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
//        /* Button Listeners **/
//        // Request Button
//        this.request = findViewById(R.id.buttonRequest);
//        this.request.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               switch (view.getId()) {
//                   // SWITCH ( must be deleted )
//                   // request
//                   case(R.id.buttonRequest):
//                       openRequestActivity();
//               }
//            }
//        });
        // upVote ImageButton
//        this.upVote = findViewById(R.id.upVote);
//        this.upVote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // call to increaseScore
//            }
//        });
//        // downVote ImageButton
//        this.downVote = findViewById(R.id.downVote);
//        this.downVote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // call to decreaseScore
//            }
//        });
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

    /**
     * Makes a call to the Request Activity
     */
    public void openRequestActivity() {
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
    }

}
