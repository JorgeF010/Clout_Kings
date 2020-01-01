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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloutkings.ui.ProfileAdapter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Version: 1.0 - Underdevelopment, Uses JSoup, AsyncTask, Classes, Dataclasses and more.
 * Author: JorgeF010 - Github, jorge-f - Linkedin
 * Description: This HomeFragment is the first fragment the user sees, It creates the profiles -
 * and fills them into a RecyclerView using their corresponding classes.
 * It instantiates the ArrayList of profiles, and a few buttons.
 * The creation of the profiles is taken care of by the CreateProfiles class, using AsyncTask and JSoup.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // Current view of the fragment
    private View view;
    private Fragment home;
    private FragmentManager homeManager;
    private FragmentTransaction homeTransaction;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert this.homeManager != null;
        this.homeManager = getFragmentManager();
        this.homeTransaction = homeManager.beginTransaction();
        this.view = inflater.inflate(R.layout.fragment_home, container, false);
        /* Profiles **/
        this.listOfProfiles = new ArrayList<>();
        /* This try / catch - will create profiles **/
        try {
            // Uses JSoup to create new profiles / persons
            addProfiles();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        // Creating the RecyclerView object
        this.mRecyclerView = view.findViewById(R.id.recyclerView);
        // Better performance + doesn't need to change in size
        this.mRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(super.getContext());
        this.mAdapter = new ProfileAdapter(this.listOfProfiles);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);

        /* Button Listeners + Initialized **/
        this.request = view.findViewById(R.id.buttonRequest);
        this.upVote = view.findViewById(R.id.upVote);
        this.downVote = view.findViewById(R.id.downVote);
        this.request.setOnClickListener(this);
        this.upVote.setOnClickListener(this);
        this.downVote.setOnClickListener(this);
        return view;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRequest:
                openRequestActivity();
                break;
            case R.id.upVote:
                // nothing for now
                break;
            case R.id.downVote:
                // nothing for now .
                break;
        }
    }

    /**
     * Makes a call to the Request Activity
     * This Request Activity Implements a HashSet to prevent from duplicate requests.
     */
    public void openRequestActivity() {
        Intent intent = new Intent(super.getContext(), RequestActivity.class);
        startActivity(intent);
    }

}
