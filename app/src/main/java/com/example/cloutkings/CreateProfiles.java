package com.example.cloutkings;
import android.os.AsyncTask;

import com.example.cloutkings.ui.Score;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CreateProfiles extends AsyncTask<String, Void, ArrayList<Profile>> {

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
        Elements namesAndAges = doc.select(".name");
        Elements famousFor = doc.select(".title");
        ArrayList<Profile> profilesFromSite = new ArrayList<>();
        Score start = new Score(0);
        int i = 0;
        String currentFamousFor;
        for (Element person : namesAndAges) {
            currentFamousFor = famousFor.get(i).text();
            Person newPerson = new Person(person.text(), "N/A");
            Profile newProfile = new Profile(R.drawable.ic_person, newPerson.getName(), currentFamousFor, newPerson, start);
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
