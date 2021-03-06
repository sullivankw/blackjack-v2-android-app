package com.sullivankw.blackjackhelper.practice;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sullivankw.blackjackhelper.AddToLeaderboardActivity;
import com.sullivankw.blackjackhelper.R;
import com.sullivankw.blackjackhelper.base.BaseActivity;
import com.sullivankw.blackjackhelper.enums.CardImage;
import com.sullivankw.blackjackhelper.enums.HandAdvice;
import com.sullivankw.blackjackhelper.jar.BlackjackHelperServiceException;
import com.sullivankw.blackjackhelper.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sullivankw.blackjackhelper.AddToLeaderboardActivity.LEADERBOARD_STORE_VALUE;

public class PracticeModeActivity extends BaseActivity implements View.OnClickListener {

    private static final String HAS_BEEN_RATED_KEY = "has_been_rated";
    private static final String PLAY_STORE_URL = "http://play.google.com/store/apps/details?id=";
    private static final String HIGH_SCORE_KEY = "high_score";
    private static final String ASKED_AMT_KEY = "asked_rating_amt";
    public static final String DELETE_LEADERBOARD_SCORE = "deleteScore";
    public static final String ADD_LEADERBOARD_SCORE = "highScore";
    public static final String LEADERBOARD_SIZE = "leaderSize";
    private static final int TIMES_TO_PLAY_BEFORE_RATING_ASK = 4;

    
    private Spinner spinner;
    private String selection;
    private ImageView imgViewDealer;
    private ImageView imgViewPCard1;
    private ImageView imgViewPCard2;
    private Button guessBtn;
    private PracticeModeViewModel viewModel;
    private TextView textViewHSValue;
    private TextView textViewCSValue;
    private int highScore;
    private static SharedPreferences sharedPref;
    private int dealerRandom;
    private int player1Random;
    private int player2Random;
    private static List<User> leaders;
    private Query leaderBoard;
    private static int oldStreak = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mode);
        setupViews();
        setupViewModel();
        setupImageResources();
        setupViewModel();
        setupLeaderBoard();

        setupBottomNav(R.id.bottom_nav_practice_layout);
        setupSpinner();
        checkSharedPreferencesStorage();
    }

    private void setupLeaderBoard() {
        leaderBoard = viewModel.getLeaderboard();
        leaderBoard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                leaders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    leaders.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //do sumn
            }
        });
    }

    private int getLowestLeaderScore() {
        //since our query is sorting on highscore, it should be first here
        if (CollectionUtils.isEmpty(leaders)) {
            return 0;
        }
        int lowScore = leaders.get(0).getHighScore();
        return lowScore;
    }

    private static User getLowest() {
        if (CollectionUtils.isEmpty(leaders)) {
            return null;
        }
        return leaders.get(0);
    }

    private static int getLeaderBoardSize() {
        if (CollectionUtils.isEmpty(leaders)) {
            return 0;
        }
        return leaders.size();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(PracticeModeViewModel.class);
        viewModel.getHandHelpResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {

                    String msg;
                    int msgLength;
                    boolean lastOneMissed = false;
                    int currentStreak = viewModel.getCurrentStreak();
                    //already been converted before being returned from service layer
                    if (s.equals(viewModel.getSelection())) {
                        msg = "Correct!";
                        currentStreak++;
                        msgLength = Toast.LENGTH_SHORT;
                    } else {
                        msg = "Nope. If dealer has " + viewModel.getDealerCard().getValue() + " you have " +
                                viewModel.getPlayerCardOne().getValue() + " and "
                                + viewModel.getPlayerCardTwo().getValue() + " you should " + s;
                        lastOneMissed = true;
                        oldStreak = currentStreak;
                        currentStreak = 0;
                        msgLength = Toast.LENGTH_LONG;
                    }
                    viewModel.resetCardValues();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            msg, msgLength);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    //could make this an observable
                    updateStreakValues(currentStreak);
                    setupImageResources();

                    if (lastOneMissed) {
                        if (oldStreak < 1) {
                            return;
                        }
                        if (getLeaderBoardSize() < LEADERBOARD_STORE_VALUE || oldStreak > getLowestLeaderScore()) {
                            PracticeModeDialog dialog = new PracticeModeDialog(false, "Your score is good enough for our leaderboard. Want to add it?");
                            dialog.show(getSupportFragmentManager(), "leaderdialog-tag");
                        }
                    }
                }
            }
        });
    }

    private void updateStreakValues(int currentStreak) {
        viewModel.setCurrentStreak(currentStreak);
        textViewCSValue.setText(String.valueOf(viewModel.getCurrentStreak()));
        if (currentStreak > highScore) {
            //set shred prefs
            sharedPref.edit().putInt(HIGH_SCORE_KEY, currentStreak).apply();
            highScore = currentStreak;
            textViewHSValue.setText(String.valueOf(highScore));
        }
    }

    private void setupViews() {
        imgViewDealer = findViewById(R.id.cardImageViewDC2);
        imgViewPCard1 = findViewById(R.id.playerCard1ImageView);
        imgViewPCard2 = findViewById(R.id.playerCard2ImageView);
        guessBtn = findViewById(R.id.guessBtn);
        guessBtn.setOnClickListener(this);
        textViewHSValue = findViewById(R.id.textViewHSValue);
        textViewCSValue = findViewById(R.id.textViewCSValue);

    }

    private void setupImageResources() {
        setRandomCards();
        if (player1Random + player2Random == 23 || player1Random + player2Random == 22
                || player1Random + player2Random == 21 || player1Random + player2Random == 20) {
            /**
             * player has blackjack, no need to guess, reset and try again
             */
            setupImageResources();
        }
        CardImage dealerCardImage = CardImage.getCardImageByPosition(dealerRandom);
        CardImage playerCardImage1 = CardImage.getCardImageByPosition(player1Random);
        CardImage playerCardImage2 = CardImage.getCardImageByPosition(player2Random);

        imgViewDealer.setImageResource(dealerCardImage.getResourceId());
        imgViewPCard1.setImageResource(playerCardImage1.getResourceId());
        imgViewPCard2.setImageResource(playerCardImage2.getResourceId());

        viewModel.setDealerCard(dealerCardImage.name());
        viewModel.setPlayerCardOne(playerCardImage1.name());
        viewModel.setPlayerCardTwo(playerCardImage2.name());
    }

    private void setRandomCards() {
        dealerRandom = new Random().nextInt(13);
        player1Random = new Random().nextInt(13);
        player2Random = new Random().nextInt(13);
    }

    public void populateSpinner() {
        List<String> items = HandAdvice.getPracticeModeSelectionValues();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.guessBtn) {
            try {
                viewModel.getHandHelp();
            } catch (BlackjackHelperServiceException e) {
                Log.e("service-error", e.toString());
                Toast.makeText(getBaseContext(), "Unexpected error. Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupSpinner() {
        spinner = findViewById(R.id.spinner);
        populateSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection = adapterView.getItemAtPosition(i).toString();
                viewModel.setSelection(selection);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void checkSharedPreferencesStorage() {
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        highScore = sharedPref.getInt(HIGH_SCORE_KEY, 0);
        textViewHSValue.setText(String.valueOf(highScore));
        textViewCSValue.setText(String.valueOf(viewModel.getCurrentStreak()));
        /**
         * add to counter. if more than 5 times the app has been used,
         * or 2 times after last dismissal we will prompt again
         * **/
        if (hasBeenRated()) {
            //all good, don't bug them again
            return;
        }
        int timesAsked = sharedPref.getInt(ASKED_AMT_KEY, 0) + 1;
        sharedPref.edit().putInt(ASKED_AMT_KEY, timesAsked).apply();
        if (timesAsked > TIMES_TO_PLAY_BEFORE_RATING_ASK) {
            PracticeModeDialog dialogFragment = new PracticeModeDialog(true, "Ratings help with visibility in the play store. Rate us?");
            dialogFragment.show(getSupportFragmentManager(), "dialog-tag");
        }

    }

    private boolean hasBeenRated() {
        return sharedPref.getBoolean(HAS_BEEN_RATED_KEY, false);
    }

    @SuppressLint("ValidFragment")
    public static class PracticeModeDialog extends DialogFragment {

        private boolean isRatingDialog;
        private String title;

        public PracticeModeDialog(boolean isRatingDialog, String title) {
            this.isRatingDialog = isRatingDialog;
            this.title = title;
        }

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

            if (getActivity() == null) {
                return null;
            }
            return new AlertDialog.Builder(getActivity()).setTitle(title)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (isRatingDialog) {
                                sharedPref.edit().putBoolean(HAS_BEEN_RATED_KEY, true).apply();
                                Uri uri = Uri.parse(PLAY_STORE_URL + getActivity().getBaseContext().getPackageName());
                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                // To count with Play market backstack, After pressing back button,
                                // to taken back to our application, we need to add following flags to intent.
                                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                try {
                                    startActivity(goToMarket);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(getContext(), "Unexpected error. Unable to connect to play store.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                //launch a new activity with summary of score, date, and an input fields for username
                                //not sharing a view model here. so just pass the data in an intent of the score
                                Intent intent = new Intent(getContext(), AddToLeaderboardActivity.class);
                                if (getLowest() != null) {
                                    intent.putExtra(DELETE_LEADERBOARD_SCORE, getLowest().getId());
                                }
                                intent.putExtra(ADD_LEADERBOARD_SCORE, oldStreak);
                                intent.putExtra(LEADERBOARD_SIZE, getLeaderBoardSize());
                                startActivity(intent);
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (isRatingDialog) {
                                sharedPref.edit().putInt(ASKED_AMT_KEY, 0).apply();
                            }
                        }
                    }).create();
        }
    }


}
