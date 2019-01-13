package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sullivankw.blackjackhelper.base.BaseActivity;
import com.sullivankw.blackjackhelper.practice.PracticeModeActivity;
import com.sullivankw.blackjackhelper.viewmodel.AddToLeaderBoardViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sullivankw.blackjackhelper.practice.PracticeModeActivity.ADD_LEADERBOARD_SCORE;
import static com.sullivankw.blackjackhelper.practice.PracticeModeActivity.DELETE_LEADERBOARD_SCORE;
import static com.sullivankw.blackjackhelper.practice.PracticeModeActivity.LEADERBOARD_SIZE;

public class AddToLeaderboardActivity extends BaseActivity {

    private EditText name;
    private boolean isValid;
    private int scoreToAdd;
    private String deleteScoreId;
    private int currentLeaderBoardSize;
    private AddToLeaderBoardViewModel viewModel;
    public static int LEADERBOARD_STORE_VALUE = 25;
    private static int MAX_NAME_SIZE = 20;
    private TextView textViewLine1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_leaderboard);
        setupBottomNav(R.id.bottom_nav_add_to_leaderboard_layout);
        setValuesFromIntent();
        setMainTextView();
        setNameInputTextListener();
        viewModel = ViewModelProviders.of(this).get(AddToLeaderBoardViewModel.class);
        setupSubmitBtn();
    }

    private void setNameInputTextListener() {
        name = findViewById(R.id.editTextName);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > MAX_NAME_SIZE) {
                    //too long
                    isValid = false;
                } else {
                    isValid = true;
                }
            }
        });
    }

    private void setupSubmitBtn() {
        findViewById(R.id.submitToLeaderBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name == null) {
                    return;
                }
                if (!isNetworkConnected()) {
                    Toast.makeText(getBaseContext(), "Connection unavailable. " +
                            "Can't add to leaderboard at this time.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getBaseContext(), PracticeModeActivity.class));
                    return;
                }
                if (isValid) {
                    String cleanName = cleanName();
                    viewModel.addToLeaderboard(cleanName, scoreToAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("save-success", this.getClass() + " save to leaderboard successful");
                            //only delete if save is successful and database leaderboard is already full
                            if (deleteScoreId == null || currentLeaderBoardSize < LEADERBOARD_STORE_VALUE) {
                                startActivity(new Intent(getBaseContext(), PracticeModeActivity.class));
                                return;
                            }
                            viewModel.deleteFromLeaderBoard(deleteScoreId).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("delete-success", this.getClass() + deleteScoreId + " was deleted from the leaderboard");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("delete-failed", this.getClass() + " unable to delete last entry in leaderboard");
                                }
                            });

                            startActivity(new Intent(getBaseContext(), PracticeModeActivity.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("save-failed", this.getClass() + " unable to save to leaderboard");
                            Toast.makeText(getBaseContext(), "Save failed. Give it another shot", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), "That name is a lil long, my friend", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String cleanName() {
        String cleanName = name.getText().toString();
        Pattern pt = Pattern.compile("[^a-zA-Z0-9 ]");
        Matcher match = pt.matcher(cleanName);
        while (match.find()) {
            String s = match.group();
            //not an error
            cleanName = cleanName.replaceAll("\\" + s, "");
        }
        return cleanName;
    }

    private void setMainTextView() {
        textViewLine1 = findViewById(R.id.textViewLine1);
        textViewLine1.setText(getResources()
                .getString(R.string.add_to_leaderboard_summary_line_1, String.valueOf(scoreToAdd)));
    }

    private void setValuesFromIntent() {
        if (getIntent() != null) {
            Bundle b = getIntent().getExtras();
            if (b == null) {
                return;
            }
            scoreToAdd = b.getInt(ADD_LEADERBOARD_SCORE);
            deleteScoreId = b.getString(DELETE_LEADERBOARD_SCORE);
            currentLeaderBoardSize = b.getInt(LEADERBOARD_SIZE);
        }
    }
}
