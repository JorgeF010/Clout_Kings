package com.example.cloutkings;

import com.example.cloutkings.ui.Score;

public class Profile{

    private int mImageResource;
    private String mText1;
    private String mText2;
    private Person person;
    private Score score;

    /**
     * The Profile object contains everything needed to represent a person on the app.
     * @param mImageResource - the Image ( profile pic )
     * @param mText1 - Large Text ( person's name )
     * @param mText2 - Smaller Text ( where they are famous ( social media platform, etc..) )
     * @param person - Person Object ( see class )
     * @param score - Score Object ( see class )
     */
    public Profile(int mImageResource, String mText1, String mText2, Person person, Score score) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.person = person;
        this.score = score;
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

    /**
     * Returns the profile's second line of text.
     * @return - String ( text )
     */
    public String getmText2() {
        return this.mText2;
    }

    /**
     * Get the score for this profile
     * @return - int ( score )
     */
    public int getScore() {
        return this.score.getScore();
    }

    /**
     * This method will be called when the downVote button is clicked.
     */
    public void decreaseScore() {
        this.score.downVoteScore();
    }

    /**
     * This method will be called when the upVote button is clicked.
     */
    public void increaseScore() {
        this.score.upVoteScore();
    }
}
