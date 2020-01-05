package com.example.cloutkings.Categories;


public class Category {

    private int mImageResource;
    private String mText1;

    /**
     * The Profile object contains everything needed to represent a person on the app.
     * @param mImageResource - the Image ( profile pic )
     * @param mText1 - Large Text ( person's name )
     */
    public Category(int mImageResource, String mText1) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
    }

    /**
     * Returns the image resource
     * @return ( int )
     */
    public int getmImageResource() {
        return this.mImageResource;
    }

    /**
     * Returns the profile's first line of text.
     * @return - String ( text )
     */
    public String getmText1() {
        return this.mText1;
    }

}
