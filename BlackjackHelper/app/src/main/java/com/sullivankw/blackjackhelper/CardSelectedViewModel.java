package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CardSelectedViewModel extends ViewModel {

    private MutableLiveData<String> dealerCard;
    private MutableLiveData<String> playerCardOne;
    private MutableLiveData<String> playerCardTwo;

    public LiveData<String> getDealerCard() {
        if (dealerCard == null) {
            dealerCard = new MutableLiveData<>();
            dealerCard.setValue(null);
        }
        return dealerCard;
    }

    public void setDealerCard(String dealerCard) {
        if (this.dealerCard == null) {
            this.dealerCard = new MutableLiveData<>();
        }
        this.dealerCard.setValue(dealerCard);
    }

    public LiveData<String> getPlayerCardOne() {
        if (playerCardOne == null) {
            playerCardOne = new MutableLiveData<>();
            playerCardOne.setValue(null);
        }
        return playerCardOne;
    }

    public void setPlayerCardOne(String playerCardOne) {
        if (this.playerCardOne == null) {
            this.playerCardOne = new MutableLiveData<>();
        }
        this.playerCardOne.setValue(playerCardOne);
    }



    public LiveData<String> getPlayerCardTwo() {
        if (playerCardTwo == null) {
            playerCardTwo = new MutableLiveData<>();
            playerCardTwo.setValue(null);
        }
        return playerCardTwo;
    }

    public void setPlayerCardTwo(String playerCardTwo) {
        if (this.playerCardTwo == null) {
            this.playerCardTwo = new MutableLiveData<>();
        }
        this.playerCardTwo.setValue(playerCardTwo);
    }
}
