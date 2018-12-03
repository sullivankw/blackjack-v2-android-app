package com.sullivankw.blackjackhelper.practice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
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

import com.sullivankw.blackjackhelper.base.BaseActivity;
import com.sullivankw.blackjackhelper.enums.CardImage;
import com.sullivankw.blackjackhelper.enums.HandAdvice;
import com.sullivankw.blackjackhelper.R;
import com.sullivankw.blackjackhelper.jar.BlackjackHelperServiceException;

import java.util.List;
import java.util.Random;

public class PracticeModeActivity extends BaseActivity implements View.OnClickListener {

    private BottomNavigationView bottomNav;
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
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mode);
        setupViews();
        setupViewModel();
        setupImageResources();
        setupViewModel();

        setupBottomNav(R.id.bottom_nav_practice_layout);
        setupSpinner();
        getFromSharedPreferences();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(PracticeModeViewModel.class);
        viewModel.getHandHelpResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    String msg;
                    int msgLength;
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
                        currentStreak = 0;
                        msgLength = Toast.LENGTH_LONG;
                    }
                    viewModel.resetCardValues();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            msg, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    //could make this an observable
                    updateStreakValues(currentStreak, msg, msgLength);
                    setupImageResources();
                }
            }
        });
    }

    private void updateStreakValues(int currentStreak, String msg, int msgLength) {
        viewModel.setCurrentStreak(currentStreak);
        textViewCSValue.setText(String.valueOf(viewModel.getCurrentStreak()));
        if (currentStreak > highScore) {
            //set shred prefs
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("high_score", currentStreak);
            editor.apply();
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
        int dealerRandom = new Random().nextInt(13);
        int player1Random = new Random().nextInt(13);
        int player2Random = new Random().nextInt(13);

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

    public void populateSpinner() {
        List<String> items = HandAdvice.getDisplayValues();
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

    private void getFromSharedPreferences() {
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        highScore = sharedPref.getInt("high_score", 0);
        textViewHSValue.setText(String.valueOf(highScore));
        textViewCSValue.setText(String.valueOf(viewModel.getCurrentStreak()));
    }
}
