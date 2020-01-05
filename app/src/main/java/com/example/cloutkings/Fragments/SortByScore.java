package com.example.cloutkings.Fragments;

import com.example.cloutkings.Profiles.Profile;

import java.util.Comparator;

public class SortByScore implements Comparator<Profile> {

    @Override
    public int compare(Profile profile, Profile other) {
        return other.getScore() - profile.getScore();
    }
}
