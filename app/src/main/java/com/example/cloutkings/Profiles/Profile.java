package com.example.cloutkings.Profiles;


public class Profile{

    private int mImageResource;
    private String mText1;
    private String mText2;
    private Person person;
    private int score;
    private String platform;
    private boolean upVoted;
    private boolean downVoted;

    /**
     * The Profile object contains everything needed to represent a person on the app.
     * @param mImageResource - the Image ( profile pic )
     * @param mText1 - Large Text ( person's name )
     * @param mText2 - Smaller Text ( where they are famous ( social media platform, etc..) )
     * @param person - Person Object ( see class )
     * @param score - score ( int )
     */
    public Profile(int mImageResource, String mText1, String mText2, Person person, int score,
                   String platform) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.person = person;
        this.score = score;
        this.platform = platform;
        this.upVoted = false;
        this.downVoted = false;
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
        return this.score;
    }

    /**
     * This method will be called when the downVote button is clicked.
     */
    public void decreaseScore() {
        if(!this.downVoted) {
            this.score -= 1;
            setDownVoted();
        }
    }

    /**
     * This method will be called when the upVote button is clicked.
     */
    public void increaseScore() {
        if(!this.upVoted) {
            this.score += 1;
            setUpVoted();
        }
    }

    /**
     * If this profile has been upVoted it can only be downVoted now
     */
    public void setUpVoted() {
        this.upVoted = true;
        if(this.downVoted) {
            this.downVoted = false;
        }
        // case 1: upVoted = true, downVoted = false
        // case 2: upVoted = false, upVoted = true
        // case 3: upVoted = true, downVoted = false, but then downVoted, so now upVoted has to be false and downVoted true
    }

    /**
     * If this profile has been downVoted it can only be upVoted now
     */
    public void setDownVoted() {
        this.downVoted = true;
        // this is for the case they decide to downVote instead
        if(this.upVoted) {
            this.upVoted = false;
        }
    }
}
