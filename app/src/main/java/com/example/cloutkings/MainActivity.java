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
import com.example.cloutkings.ui.home.HomeFragment;
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

    // Recycler - created in xml
    private RecyclerView mRecyclerView;
    // bridge between our data and our recycler ^^
    private RecyclerView.Adapter mAdapter;
    // aligns our profiles in the list
    private RecyclerView.LayoutManager mLayoutManager;
    // Person Name
    private ArrayList<Profile> listOfProfiles;
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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_categories, R.id.navigation_trending)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        // Nav Click Listener - calls method
        navView.setOnNavigationItemSelectedListener(navListener);
        /* Profiles **/
        this.listOfProfiles = new ArrayList<>();
        /* This try / catch - will create profiles **/
        try {
            addProfiles();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mRecyclerView = findViewById(R.id.recyclerView);
        this.mRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.mAdapter = new ProfileAdapter(this.listOfProfiles);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
        /* Button Listeners **/
        // Request Button
        this.request = findViewById(R.id.buttonRequest);
        this.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               switch (view.getId()) {
                   // SWITCH ( must be deleted )
                   // request
                   case(R.id.buttonRequest):
                       openRequestActivity();
               }
            }
        });
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
//                case R.id.navigation_trending:

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
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

    /**
     * Uses AsyncTask to concurrently run a thread to make a http call.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void addProfiles() throws ExecutionException, InterruptedException {
        AsyncTask<String, Void, ArrayList<Profile>> profiles = new CreateProfiles(new CreateProfiles.AsyncResponse() {
            @Override
            public void processFinish(ArrayList<Profile> output) {

            }
        }).execute("https://www.famousbirthdays.com/most-popular-people.html");
        this.listOfProfiles = profiles.get();
    }

}
