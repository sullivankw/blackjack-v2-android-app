package com.sullivankw.blackjackhelper.practice;

import com.google.firebase.database.Query;
import com.sullivankw.blackjackhelper.base.BaseViewModel;
import com.sullivankw.blackjackhelper.firebase.FirebaseRepo;

public class PracticeModeViewModel extends BaseViewModel {

    private String selection;
    private int currentStreak;

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Query getLeaderboard() {
        return FirebaseRepo.getFirebaseRepo().getSortedLeaderboard();
    }

}
