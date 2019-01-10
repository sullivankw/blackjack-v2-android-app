package com.sullivankw.blackjackhelper;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sullivankw.blackjackhelper.model.User;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardRecViewAdaptor extends RecyclerView.Adapter<LeaderBoardRecViewAdaptor.SingleLeaderHolder> {

    private List<User> users;

    public LeaderBoardRecViewAdaptor() {
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public SingleLeaderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        return new SingleLeaderHolder(inflater.inflate(R.layout.leader_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SingleLeaderHolder singleLeaderHolder, int position) {
        final User user = users.get(position);
        Resources res = singleLeaderHolder.itemView.getContext().getResources();
        singleLeaderHolder.getLeaderCreated().setText(user.getDisplayCreated());
        singleLeaderHolder.getLeaderName().setText(res.getString(R.string.user_in_leaderboard,
                String.valueOf(position + 1) + ". ", user.getUsername()));
        singleLeaderHolder.getLeaderScore().setText(String.valueOf(user.getHighScore()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setLeaderUsersForRecView(List<User> users) {
        if (users != null) {
            this.users = users;
        }
        notifyDataSetChanged();
    }

    public class SingleLeaderHolder extends RecyclerView.ViewHolder {

        private TextView leaderName;
        private TextView leaderScore;
        private TextView leaderCreated;

        public SingleLeaderHolder(@NonNull View itemView) {
            super(itemView);
            leaderName = itemView.findViewById(R.id.leaderName);
            leaderScore = itemView.findViewById(R.id.leaderScore);
            leaderCreated = itemView.findViewById(R.id.leaderCreated);

        }

        public TextView getLeaderName() {
            return leaderName;
        }

        public TextView getLeaderScore() {
            return leaderScore;
        }

        public TextView getLeaderCreated() {
            return leaderCreated;
        }
    }
}
