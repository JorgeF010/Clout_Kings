package com.example.cloutkings;

public class Profile{

    private int mImageResource;
    private String mText1;
    private String mText2;
    private Person person;

    public Profile(int mImageResource, String mText1, String mText2, Person person) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.person = person;
    }

    public int getmImageResource() {
        return this.mImageResource;
    }

    public String getmText1() {
        return this.mText1;
    }

    public String getmText2() {
        return this.mText2;
    }
}
