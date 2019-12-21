package com.example.cloutkings;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

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


//    public Person(String name, String instagram, String tickTock, String youTube) {
//        this.name = name;
//        try {
//            this.Instagram = new URL(instagram);
//            this.TickTock = new URL(tickTock);
//            this.YouTube = new URL(youTube);
//        }
//        catch (MalformedURLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    public Person(String name, String instagram, String youTube) {
//
//    }
//
//    public Person(String name, String instagram, String tickTock) {
//
//    }

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
