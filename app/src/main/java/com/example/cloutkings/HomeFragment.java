package com.example.cloutkings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

/**
 * Version: 1.0 - Underdevelopment, Uses JSoup, AsyncTask, Classes, Dataclasses and more.
 * Author: JorgeF010 - Github, jorge-f - Linkedin
 * Description: This HomeFragment is the first fragment the user sees, It creates the profiles -
 * and fills them into a RecyclerView using their corresponding classes.
 * It instantiates the ArrayList of profiles, and a few buttons.
 * The creation of the profiles is taken care of by the CreateProfiles class, using AsyncTask and JSoup.
 */
public class HomeFragment extends Fragment  {

    // Current view of the fragment
    private View view;
    private Fragment home;
    private FragmentManager homeManager;
    private FragmentTransaction homeTransaction;
    // Recycler - created in xml
    private RecyclerView mRecyclerView;
    // bridge between our data and our recycler ^^
    private ProfileAdapter mAdapter;
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
    // Ad
    private AdView adView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Makes sure beginTransaction is never null
        assert this.homeManager != null;
        this.homeManager = getFragmentManager();
        this.homeTransaction = homeManager.beginTransaction();
        this.view = inflater.inflate(R.layout.fragment_home, container, false);
        // Calls made to display home fragment
        createProfiles();
        buildRecyclerView();
        setButtons();
        setAds();
        return view;
    }

    /**
     * Initializes the Array of Profiles and then calls addProfiles()
     * Exceptions: May catch an ExecutionException or an InterruptedException.
     */
    public void createProfiles() {
        /* Profiles **/
        this.listOfProfiles = new ArrayList<>();
        /* This try / catch - will create profiles **/
        try {
            // Uses JSoup to create new profiles / persons
            addProfiles();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds the RecyclerView and the upVote and downVote ImageButtons
     */
    public void buildRecyclerView() {
        // Creating the RecyclerView object
        this.mRecyclerView = view.findViewById(R.id.recyclerView);
        // Better performance + doesn't need to change in size
        this.mRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(super.getContext());
        this.mAdapter = new ProfileAdapter(this.listOfProfiles);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
        // Adds a space in between profiles
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        this.mAdapter.setOnClickListener(new ProfileAdapter.OnItemClickListener() {
            @Override
            public void onUpVoteClick(int position) {
                moveUp(position);
            }

            @Override
            public void onDownVoteClick(int position) {
                moveDown(position);
            }
        });

    }

    /**
     * Instantiates all buttons and their corresponding click listeners
     */
    public void setButtons() {
        /* Button Listeners + Initialized **/
        this.request = view.findViewById(R.id.buttonRequest);
        this.upVote = view.findViewById(R.id.upVote);
        this.downVote = view.findViewById(R.id.downVote);
        this.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.buttonRequest) {
                    openRequestActivity();
                }
            }
        });
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

    /**
     * Makes a call to the Request Activity
     * This Request Activity Implements a HashSet to prevent from duplicate requests.
     */
    public void openRequestActivity() {
        Intent intent = new Intent(super.getContext(), RequestActivity.class);
        startActivity(intent);
    }

    /**
     * Setups up Google Ads on the HomeFragment page
     */
    public void setAds() {
        MobileAds.initialize(this.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        this.adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        this.adView.loadAd(adRequest);
    }

    public void moveUp(int position) {
        Profile current = this.listOfProfiles.get(position);
        current.increaseScore();
        Collections.sort(listOfProfiles, new SortByScore());
        int updatedIndex = this.listOfProfiles.indexOf(current);
        this.mAdapter.notifyItemMoved(position, updatedIndex);
    }

    public void moveDown(int position) {
        // prevents index out of bounds
        Profile current = this.listOfProfiles.get(position);
        current.decreaseScore();
        Collections.sort(listOfProfiles, new SortByScore());
        int updatedIndex = this.listOfProfiles.indexOf(current);
        this.mAdapter.notifyItemMoved(position, updatedIndex);
    }

    class SortByScore implements Comparator<Profile> {

        @Override
        public int compare(Profile profile, Profile other) {
            return other.getScore() - profile.getScore();
        }
    }

}
