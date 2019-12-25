package com.example.cloutkings.ui;

import androidx.annotation.NonNull;

public class Score {

    // Score for this profile
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void downVoteScore() {
        this.score -= 1;
    }

    public void upVoteScore() {
        this.score += 1;
    }

    public boolean equals(@NonNull Score other) {
        return this.getScore() == other.getScore();
    }
}
