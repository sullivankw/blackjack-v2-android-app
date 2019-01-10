package com.sullivankw.blackjackhelper.gethelpflow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sullivankw.blackjackhelper.viewmodel.CardSelectedViewModel;
import com.sullivankw.blackjackhelper.R;
import com.sullivankw.blackjackhelper.base.BaseFragment;

public class PlayerCardTwoFragment extends BaseFragment implements View.OnClickListener {

    private ImageView buttonTwo;
    private ImageView buttonThree;
    private ImageView buttonFour;
    private ImageView buttonFive;
    private ImageView buttonSix;
    private ImageView buttonSeven;
    private ImageView buttonEight;
    private ImageView buttonNine;
    private ImageView buttonTen;
    private ImageView buttonJack;
    private ImageView buttonQueen;
    private ImageView buttonKing;
    private ImageView buttonAce;

    private int[] buttonIdArray;
    private CardSelectedViewModel viewModel;

    public PlayerCardTwoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_card_two, container, false);
        createBtnIdArray();
        setupViews(view);
        setupListeners();
        viewModel = getViewModel();

        return view;
    }

    private void createBtnIdArray() {
        buttonIdArray = new int[13];
        buttonIdArray[0] = R.id.cardImageViewP2C2;
        buttonIdArray[1] = R.id.cardImageViewP2C3;
        buttonIdArray[2] = R.id.cardImageViewP2C4;
        buttonIdArray[3] = R.id.cardImageViewP2C5;
        buttonIdArray[4] = R.id.cardImageViewP2C6;
        buttonIdArray[5] = R.id.cardImageViewP2C7;
        buttonIdArray[6] = R.id.cardImageViewP2C8;
        buttonIdArray[7] = R.id.cardImageViewP2C9;
        buttonIdArray[8] = R.id.cardImageViewP2CT;
        buttonIdArray[9] = R.id.cardImageViewP2CJ;
        buttonIdArray[10] = R.id.cardImageViewP2CQ;
        buttonIdArray[11] = R.id.cardImageViewP2CK;
        buttonIdArray[12] = R.id.cardImageViewP2CA;
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
        ImageView[] buttons = setupButtonViews(view, buttonIdArray);

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
        viewModel.setPlayerCardTwo(card);
        viewModel.setNextPage(3);
    }

}
