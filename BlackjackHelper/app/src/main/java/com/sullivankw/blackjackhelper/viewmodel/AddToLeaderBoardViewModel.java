package com.sullivankw.blackjackhelper.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.sullivankw.blackjackhelper.firebase.FirebaseRepo;

public class AddToLeaderBoardViewModel extends ViewModel {

    public Task<Void> addToLeaderboard(String name, int scoreToAdd) {
        return FirebaseRepo.getFirebaseRepo().addToLeaderboard(name, scoreToAdd);
    }

    public Task<Void> deleteFromLeaderBoard(String deleteScoreId) {
        return FirebaseRepo.getFirebaseRepo().deleteLeader(deleteScoreId);
    }
}
