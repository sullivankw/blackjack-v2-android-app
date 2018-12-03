package com.sullivankw.blackjackhelper.practice;

import com.sullivankw.blackjackhelper.base.BaseViewModel;

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

}
