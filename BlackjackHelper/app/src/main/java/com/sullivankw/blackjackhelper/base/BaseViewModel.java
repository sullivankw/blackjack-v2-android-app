package com.sullivankw.blackjackhelper.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sullivankw.blackjackhelper.jar.BlackjackHelperServiceException;
import com.sullivankw.blackjackhelper.network.AndroidHandHelperService;

public class BaseViewModel extends ViewModel {

    private MutableLiveData<String> dealerCard;
    private MutableLiveData<String> playerCardOne;
    private MutableLiveData<String> playerCardTwo;
    private MutableLiveData<String> handHelp;
    private MutableLiveData<Throwable> t;

    private AndroidHandHelperService androidHandHelperService;
    private boolean hitSoft17;
    //REST API is available but not implemented until other client apps need to hit the service
    private boolean overHttp = false;


    public BaseViewModel() {
            androidHandHelperService = AndroidHandHelperService.getAndroidHandHelperService();
    }
    public String getHandHelp() throws BlackjackHelperServiceException {
        return androidHandHelperService.getHandHelp(getHitSoft17(), dealerCard.getValue(),
                playerCardOne.getValue(), playerCardTwo.getValue(), this, overHttp);
    }

    public void setHitSoft17(boolean hitSoft17) {
        this.hitSoft17 = hitSoft17;

    }

    public boolean getHitSoft17() {
        return hitSoft17;
    }

    public void setNetworkError(Throwable t) {
        if (this.t == null) {
            this.t = new MutableLiveData<>();
        }
        this.t.setValue(t);
    }

    public LiveData<Throwable> getNetworkError() {
        if (t == null) {
            t = new MutableLiveData<>();
            t.setValue(null);
        }
        return t;
    }

    public void setHandHelpResponse(String handHelp) {
        if (this.handHelp == null) {
            this.handHelp = new MutableLiveData<>();
        }
        this.handHelp.setValue(handHelp);
    }

    public LiveData<String> getHandHelpResponse() {
        if (handHelp == null) {
            handHelp = new MutableLiveData<>();
            handHelp.setValue(null);
        }
        return handHelp;
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

    public void resetValues() {
        playerCardTwo.setValue(null);
        playerCardOne.setValue(null);
        dealerCard.setValue(null);
        handHelp.setValue(null);
    }
}
