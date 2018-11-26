package com.sullivankw.blackjackhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentDealerCard extends BaseFragment implements View.OnClickListener {

    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonTen;
    private Button buttonJack;
    private Button buttonQueen;
    private Button buttonKing;
    private Button buttonAce;

    private CardSelectedViewModel viewModel;
    private int[] buttonIdArray;

    public FragmentDealerCard() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dealer_card, container, false);
        createBtnIdArray();
        setupViews(view);
        setupListeners();
        viewModel = getViewModel();
        return view;
    }

    private void createBtnIdArray() {
        buttonIdArray = new int[13];
        buttonIdArray[0] = R.id.buttonTwo;
        buttonIdArray[1] = R.id.buttonThree;
        buttonIdArray[2] = R.id.buttonFour;
        buttonIdArray[3] = R.id.buttonFive;
        buttonIdArray[4] = R.id.buttonSix;
        buttonIdArray[5] = R.id.buttonSeven;
        buttonIdArray[6] = R.id.buttonEight;
        buttonIdArray[7] = R.id.buttonNine;
        buttonIdArray[8] = R.id.buttonTen;
        buttonIdArray[9] = R.id.buttonJack;
        buttonIdArray[10] = R.id.buttonQueen;
        buttonIdArray[11] = R.id.buttonKing;
        buttonIdArray[12] = R.id.buttonAce;
    }

    private void setupListeners() {

        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonTen.setOnClickListener(this);
        buttonJack.setOnClickListener(this);
        buttonQueen.setOnClickListener(this);
        buttonKing.setOnClickListener(this);
        buttonAce.setOnClickListener(this);
    }

    private void setupViews(View view) {
        Button[] buttons = setupButtonViews(view, buttonIdArray);

        buttonTwo = buttons[0];
        buttonThree = buttons[1];
        buttonFour = buttons[2];
        buttonFive = buttons[3];
        buttonSix = buttons[4];
        buttonSeven = buttons[5];
        buttonEight = buttons[6];
        buttonNine = buttons[7];
        buttonTen = buttons[8];
        buttonJack = buttons[9];
        buttonQueen = buttons[10];
        buttonKing = buttons[11];
        buttonAce = buttons[12];
    }


    @Override
    public void onClick(View v) {

        String card = convertButtonClickToCardSelected(v.getId(), buttonIdArray);

        viewModel.setDealerCard(card);
        viewModel.setNextPage(1);
    }
}
