package com.sullivankw.blackjackhelper.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.sullivankw.blackjackhelper.CardSelectedViewModel;


public class BaseFragment extends Fragment {

    public String convertButtonClickToCardSelected(int viewIdSelected, int [] buttonIdArray) throws IllegalArgumentException {

        if (viewIdSelected == buttonIdArray[0]) {
            return "two";
        }
        if (viewIdSelected == buttonIdArray[1]) {
            return "three";
        }
        if (viewIdSelected == buttonIdArray[2]) {
            return "four";
        }
        if (viewIdSelected == buttonIdArray[3]) {
            return "five";
        }
        if (viewIdSelected == buttonIdArray[4]) {
            return "six";
        }
        if (viewIdSelected == buttonIdArray[5]) {
            return "seven";
        }
        if (viewIdSelected == buttonIdArray[6]) {
            return "eight";
        }
        if (viewIdSelected == buttonIdArray[7]) {
            return "nine";
        }
        if (viewIdSelected ==buttonIdArray[8]) {
            return "ten";
        }
        if (viewIdSelected == buttonIdArray[9]) {
            return "jack";
        }
        if (viewIdSelected == buttonIdArray[10]) {
            return "queen";
        }
        if (viewIdSelected == buttonIdArray[11]) {
            return "king";
        }
        if (viewIdSelected == buttonIdArray[12]) {
            return "ace";
        }
        throw new IllegalArgumentException();
    }


    public Button[] setupButtonViews(View view, int [] buttonIdArray) {

        Button buttonTwo = view.findViewById(buttonIdArray[0]);
        Button buttonThree = view.findViewById(buttonIdArray[1]);
        Button buttonFour = view.findViewById(buttonIdArray[2]);
        Button buttonFive = view.findViewById(buttonIdArray[3]);
        Button buttonSix = view.findViewById(buttonIdArray[4]);
        Button buttonSeven = view.findViewById(buttonIdArray[5]);
        Button buttonEight = view.findViewById(buttonIdArray[6]);
        Button buttonNine = view.findViewById(buttonIdArray[7]);
        Button buttonTen = view.findViewById(buttonIdArray[8]);
        Button buttonJack = view.findViewById(buttonIdArray[9]);
        Button buttonQueen = view.findViewById(buttonIdArray[10]);
        Button buttonKing = view.findViewById(buttonIdArray[11]);
        Button buttonAce = view.findViewById(buttonIdArray[12]);

        return new Button[]{buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight,
                buttonNine, buttonTen, buttonJack, buttonQueen, buttonKing, buttonAce
        };

    }

    public CardSelectedViewModel getViewModel() throws ActivityNotFoundException {
        if (getActivity() == null) {
            throw new ActivityNotFoundException("activity not available to retrieve view model");
        }
        return ViewModelProviders.of(getActivity()).
                get(CardSelectedViewModel.class);
    }

}