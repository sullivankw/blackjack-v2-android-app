package com.sullivankw.blackjackhelper;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public class CardSelectedViewModel extends BaseViewModel {

    private MutableLiveData<Integer> nextPage;

    public void setNextPage(Integer nextPage) {
        if (this.nextPage == null) {
            this.nextPage = new MutableLiveData<>();
        }
        this.nextPage.setValue(nextPage);
    }

    public LiveData<Integer> getNextPage() {
        if (nextPage == null) {
            nextPage = new MutableLiveData<>();
            nextPage.setValue(null);
        }
        return nextPage;
    }
}
