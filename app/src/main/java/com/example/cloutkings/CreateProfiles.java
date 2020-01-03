package com.example.cloutkings;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Version: 1.0 - Underdevelopment, Uses JSoup, AsyncTask, Classes, Dataclasses and more.
 * Author: JorgeF010 - Github, jorge-f - Linkedin
 * Description: This is the most important part of the APP thus far, it uses AsyncTask to let the -
 * main thread work concurrently with the task of the HTTP call.
 * It takes in a String ( URL ) ( arg 1 ), no progress is needed ( arg 2 ), and the result is an -
 * ArrayList of profiles.
 * This list is then used to populate the HomeFragment with profiles visible to the user.
 * It holds a nested interface to help with the return value of onPostExecute
 */
public class CreateProfiles extends AsyncTask<String, Void, ArrayList<Profile>> {

    // Instagram + Facebook + YouTube
    private ArrayList<Profile> socialMedia;
    private ArrayList<Profile> TikTok;
//    private ArrayList<Profile> dancer;
//    private ArrayList<Profile> realityStar;
    private ArrayList<Profile> rapper;
    // Both Movie Actors and TV Actors
//    private ArrayList<Profile> actors;
    private ArrayList<Profile> sports;
    private ArrayList<Profile> twitch;

    // Interface to get result
    public interface AsyncResponse {
        void processFinish(ArrayList<Profile> output);
    }

    public AsyncResponse delegate = null;

    public CreateProfiles(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ArrayList<Profile> doInBackground(String... urls) {
        Document doc = null;
        try {
            doc = Jsoup.connect(urls[0]).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // CSS Query used to select data
        Elements namesAndAges = doc.select(".name");
        // Reason to why they are famous
        Elements famousFor = doc.select(".title");
        // ArrayList which the created profiles will be added to
        ArrayList<Profile> profilesFromSite = new ArrayList<>();
        // By default all profiles start with a score of 0
        int score = 0;
        // count kept to access the correct index from the profilesFromSite array
        int i = 0;
        String currentFamousFor;
        // for-loop needed to iterate through both nameAndAges and famousFor data
        for (Element person : namesAndAges) {
            currentFamousFor = famousFor.get(i).text();
            Person newPerson = new Person(person.text(), "N/A");
            Profile newProfile = new Profile(R.drawable.ic_person, newPerson.getName(), currentFamousFor, newPerson,
                    score, currentFamousFor);
            profilesFromSite.add(newProfile);
            i++;
        }
        return profilesFromSite;
    }

    @Override
    protected void onPostExecute(ArrayList<Profile> result) {
        this.delegate.processFinish(result);
    }
}
