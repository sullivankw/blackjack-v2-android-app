package com.sullivankw.blackjackhelper.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.Query;
import com.sullivankw.blackjackhelper.firebase.FirebaseRepo;

public class LeaderBoardViewModel extends ViewModel {

    public Query getLeaderboard() {
        return FirebaseRepo.getFirebaseRepo().getSortedLeaderboard();
    }
}
