package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sullivankw.blackjackhelper.base.BaseActivity;
import com.sullivankw.blackjackhelper.model.User;
import com.sullivankw.blackjackhelper.viewmodel.LeaderBoardViewModel;

import java.util.ArrayList;
import java.util.List;


public class LeaderBoardActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LeaderBoardRecViewAdaptor recViewAdaptor;
    private Query currentLeaderBoard;
    private List<User> leaders;
    private LeaderBoardViewModel viewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        setupBottomNav(R.id.bottom_nav_leader_layout);
        setupRecyclerView();
        viewModel = ViewModelProviders.of(this).get(LeaderBoardViewModel.class);
        setupLeaderBoard();
    }

    private void setupLeaderBoard() {
        currentLeaderBoard = viewModel.getLeaderboard();
        currentLeaderBoard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                leaders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    leaders.add(user);
                }
                recViewAdaptor.setLeaderUsersForRecView(reverseQueryOrder(leaders));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //do sumn
            }
        });
    }

    /****
     *
     * Method needed to sort order in reverse. Firebase doesn't allow that order to be returned from database
     */
    private List<User> reverseQueryOrder(List<User> leaders) {
        List<User> reverseLeaders = new ArrayList<>();

        for (int i = leaders.size() - 1; i >= 0; i -- ) {
            reverseLeaders.add(leaders.get(i));
        }
        return reverseLeaders;
    }


    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.leaderBoardRecView);
        recViewAdaptor = new LeaderBoardRecViewAdaptor();
        recyclerView.setAdapter(recViewAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    }
}
