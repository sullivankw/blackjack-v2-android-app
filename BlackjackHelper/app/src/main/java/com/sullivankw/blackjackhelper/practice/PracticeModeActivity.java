package com.sullivankw.blackjackhelper.practice;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sullivankw.blackjackhelper.base.BaseActivity;
import com.sullivankw.blackjackhelper.CardImage;
import com.sullivankw.blackjackhelper.HandAdvice;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mode);
        setupViews();
        setupViewModel();
        setupImageResources();
        setupViewModel();

        bottomNav = setupBottomNav(R.id.bottom_nav_practice_layout);
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

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(PracticeModeViewModel.class);
        viewModel.getHandHelpResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    String msg;
                    //already been converted before being returned from service layer
                    if (s.equals(viewModel.getSelection())) {
                        msg = "Correct!";
                    } else {
                        msg = "Nope. If dealer has " + viewModel.getDealerCard().getValue() + " you have " +
                                viewModel.getPlayerCardOne().getValue() + " and "
                                + viewModel.getPlayerCardTwo().getValue() + " You should: " + s;
                    }
                    viewModel.resetValues();
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                    setupImageResources();
                }
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
                //TODO
                e.printStackTrace();
            }
        }
    }
}
