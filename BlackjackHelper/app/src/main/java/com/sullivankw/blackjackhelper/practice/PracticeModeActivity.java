package com.sullivankw.blackjackhelper.practice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sullivankw.blackjackhelper.BaseActivity;
import com.sullivankw.blackjackhelper.CardImage;
import com.sullivankw.blackjackhelper.HandAdvice;
import com.sullivankw.blackjackhelper.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mode);
        setupViews();
        setupImageResources();
        viewModel = ViewModelProviders.of(this).get(PracticeModeViewModel.class);
        viewModel.getAdviceFromNetworkResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    String msg;
                    if (HandAdvice.fromEnum(s).equals(selection)) {
                        msg = "Correct!";
                    } else {
                        msg = "You should of: " + s;
                    }
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                    setupImageResources();
                }
            }
        });
        bottomNav = setupBottomNav(R.id.bottom_nav_practice_layout);
        spinner = findViewById(R.id.spinner);
        populateSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupViews() {
        imgViewDealer = findViewById(R.id.dealerCardImageView);
        imgViewPCard1 = findViewById(R.id.playerCard1ImageView);
        imgViewPCard2 = findViewById(R.id.playerCard2ImageView);
        guessBtn = findViewById(R.id.guessBtn);
        guessBtn.setOnClickListener(this);

    }

    private void setupImageResources() {
        int dealerRandom = new Random().nextInt(13);
        int player1Random = new Random().nextInt(13);
        int player2Random = new Random().nextInt(13);

        imgViewDealer.setImageResource(CardImage.getResourceIdByPosition(dealerRandom));
        imgViewPCard1.setImageResource(CardImage.getResourceIdByPosition(player1Random));
        imgViewPCard2.setImageResource(CardImage.getResourceIdByPosition(player2Random));
    }

    private void populateSpinner() {
        //TODO if players cards show blackjack supress and retry
        List<String> items = HandAdvice.getDisplayValues();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.guessBtn) {
            viewModel.getAdvice();
        }
    }
}
