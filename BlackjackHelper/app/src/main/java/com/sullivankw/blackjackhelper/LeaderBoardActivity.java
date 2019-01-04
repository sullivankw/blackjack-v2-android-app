package com.sullivankw.blackjackhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sullivankw.blackjackhelper.base.BaseActivity;
import com.sullivankw.blackjackhelper.firebase.FirebaseUtils;
import com.sullivankw.blackjackhelper.model.User;

import java.util.ArrayList;
import java.util.List;


public class LeaderBoardActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LeaderBoardRecViewAdaptor recViewAdaptor;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        setupBottomNav(R.id.bottom_nav_leader_layout);
        setupRecyclerView();
    }


    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.leaderBoardRecView);
        recViewAdaptor = new LeaderBoardRecViewAdaptor();
        recyclerView.setAdapter(recViewAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        recViewAdaptor.setLeaderUsersForRecView(FirebaseUtils.getLeaderBoard());
    }
}
