package com.sullivankw.blackjackhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentPlayerCardOne extends BaseFragment implements View.OnClickListener {

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

    private int[] buttonIdArray;
    private CardSelectedViewModel viewModel;

    public FragmentPlayerCardOne() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_card_one, container, false);
        createBtnIdArray();
        setupViews(view);
        setupListeners();
        viewModel = getViewModel();

        return view;
    }

    private void createBtnIdArray() {
        buttonIdArray = new int[13];
        buttonIdArray[0] = R.id.playerCardOneBtnTwo;
        buttonIdArray[1] = R.id.playerCardOneBtnThree;
        buttonIdArray[2] = R.id.playerCardOneBtnFour;
        buttonIdArray[3] = R.id.playerCardOneBtnFive;
        buttonIdArray[4] = R.id.playerCardOneBtnSix;
        buttonIdArray[5] = R.id.playerCardOneBtnSeven;
        buttonIdArray[6] = R.id.playerCardOneBtnEight;
        buttonIdArray[7] = R.id.playerCardOneBtnNine;
        buttonIdArray[8] = R.id.playerCardOneBtnTen;
        buttonIdArray[9] = R.id.playerCardOneBtnJack;
        buttonIdArray[10] = R.id.playerCardOneBtnQueen;
        buttonIdArray[11] = R.id.playerCardOneBtnKing;
        buttonIdArray[12] = R.id.playerCardOneBtnAce;
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

        viewModel.setPlayerCardOne(card);
        viewModel.setNextPage(2);

    }
}
