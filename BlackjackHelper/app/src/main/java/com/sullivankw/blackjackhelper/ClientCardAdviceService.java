package com.sullivankw.blackjackhelper;

public interface ClientCardAdviceService {

    String getAdvice(boolean hitSoft, String dealerCard,
                            String playerCard1, String playerCard2, BaseViewModel viewModel);
}
