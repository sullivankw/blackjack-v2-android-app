package com.sullivankw.blackjackhelper.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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


    public ImageView[] setupButtonViews(View view, int [] buttonIdArray) {

        ImageView buttonTwo = view.findViewById(buttonIdArray[0]);
        ImageView buttonThree = view.findViewById(buttonIdArray[1]);
        ImageView buttonFour = view.findViewById(buttonIdArray[2]);
        ImageView buttonFive = view.findViewById(buttonIdArray[3]);
        ImageView buttonSix = view.findViewById(buttonIdArray[4]);
        ImageView buttonSeven = view.findViewById(buttonIdArray[5]);
        ImageView buttonEight = view.findViewById(buttonIdArray[6]);
        ImageView buttonNine = view.findViewById(buttonIdArray[7]);
        ImageView buttonTen = view.findViewById(buttonIdArray[8]);
        ImageView buttonJack = view.findViewById(buttonIdArray[9]);
        ImageView buttonQueen = view.findViewById(buttonIdArray[10]);
        ImageView buttonKing = view.findViewById(buttonIdArray[11]);
        ImageView buttonAce = view.findViewById(buttonIdArray[12]);

        return new ImageView[]{buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight,
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