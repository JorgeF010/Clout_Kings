package com.example.cloutkings;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HomeFragment extends Fragment {

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
}
