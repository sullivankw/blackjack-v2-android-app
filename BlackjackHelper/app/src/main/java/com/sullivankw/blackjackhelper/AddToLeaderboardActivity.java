package com.sullivankw.blackjackhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sullivankw.blackjackhelper.base.BaseActivity;

public class AddToLeaderboardActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_leaderboard);
        setupBottomNav(R.id.bottom_nav_add_to_leaderboard_layout);
    }
}
