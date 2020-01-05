package com.example.cloutkings.Profiles;

import java.net.MalformedURLException;
import java.net.URL;

public class Person {

    private String name;
    private URL Instagram;
    private URL TickTock;
    private URL YouTube;


    public Person(String name, String instagram) {
        this.name = name;
        try {
            this.Instagram = new URL(instagram);
        }
        catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public String getName() {
        return name;
    }

    public URL getInstagram() {
        return Instagram;
    }

    public URL getTickTock() {
        return TickTock;
    }

    public URL getYouTube() {
        return YouTube;
    }
}
