package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CardSelectedViewModel extends ViewModel {

    private MutableLiveData<String> dealerCard;
    private MutableLiveData<String> playerCardOne;
    private MutableLiveData<String> playerCardTwo;

    private ClientCardAdviceServiceNetworkImpl clientCardAdviceServiceNetworkImpl;
    private MutableLiveData<String> advice;
    private MutableLiveData<Throwable> t;


    public CardSelectedViewModel() {
        if (clientCardAdviceServiceNetworkImpl == null) {
            clientCardAdviceServiceNetworkImpl = new ClientCardAdviceServiceNetworkImpl();
        }
    }

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

    public String getAdvice() {
        //TODO boolean
        return clientCardAdviceServiceNetworkImpl.getAdvice(false, dealerCard.getValue(),
                playerCardOne.getValue(), playerCardTwo.getValue(), this);
    }

    public void setAdviceFromNetworkResponse(String advice) {
        if (this.advice == null) {
            this.advice = new MutableLiveData<>();
        }
        this.advice.setValue(advice);
    }

    public void setNetworkError(Throwable t) {
        if (this.t == null) {
            this.t = new MutableLiveData<>();
        }
        this.t.setValue(t);
    }

    public LiveData<String> getAdviceFromNetworkResponse() {
        if (advice == null) {
            advice = new MutableLiveData<>();
            advice.setValue(null);
        }
        return advice;
    }

    public LiveData<Throwable> getNetworkError() {
        if (t == null) {
            t = new MutableLiveData<>();
            t.setValue(null);
        }
        return t;
    }

    public void resetValues() {
        playerCardOne = new MutableLiveData<>();
        playerCardTwo = new MutableLiveData<>();
        dealerCard = new MutableLiveData<>();
        advice = new MutableLiveData<>();
    }
}
