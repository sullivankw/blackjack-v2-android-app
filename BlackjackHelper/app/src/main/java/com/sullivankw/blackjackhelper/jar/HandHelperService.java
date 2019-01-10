package com.sullivankw.blackjackhelper.jar;

import com.sullivankw.blackjackhelper.enums.HandAdvice;

public class HandHelperService {
    private static HandHelperService handHelperService;

    private HandHelperService() {
    }

    public synchronized static HandHelperService getHandHelperService() {
        if (handHelperService == null) {
            handHelperService = new HandHelperService();
        }
        return handHelperService;
    }

	public String getHandHelp(boolean hitSoft, String dealerCard, String playerCard1, String playerCard2) throws BlackjackHelperServiceException {

		if (hitSoft) {
			return getHelpForHitOnSoft17(dealerCard, playerCard1, playerCard2);
			
		} else {
			return getHelpForStayOnSoft17(dealerCard, playerCard1, playerCard2);
		}
	}
	
	private String getHelpForHitOnSoft17(String dealerCard, String playerCard1, String playerCard2) throws BlackjackHelperServiceException {
		
		int dealerCardInt = 0;
		int playerCardAInt = 0;
		int playerCardBInt = 0;
		int playerCardSum = 0;
		try {
        dealerCardInt = CardValue.fromCardValue(dealerCard);
        playerCardAInt = CardValue.fromCardValue(playerCard1);
        playerCardBInt = CardValue.fromCardValue(playerCard2);
        playerCardSum = playerCardAInt + playerCardBInt;
		} catch (IllegalArgumentException e) {
			throw new BlackjackHelperServiceException(e.getMessage());
		}

		if (playerCardSum == 21) {
		    return HandAdvice.BLACKJACK.name();
        }
		
        //------------------HANDLES SOFT ACES---------------------------------
        if (playerCard1.equals("A") || playerCard2.equals("A") && playerCard1 != playerCard2) {
            switch(dealerCardInt) {
                case 2:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 ||
                            playerCardSum == 16 || playerCardSum == 17) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 3:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 ||
                            playerCardSum == 16) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 17) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 4:
                    if (playerCardSum == 13 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 15 || playerCardSum == 16 || playerCardSum == 17) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 5:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 ) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 6:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 ) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 7:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 ) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 8:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 ) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 9:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 || playerCardSum == 18) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 10:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 || playerCardSum == 18) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 11:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 || playerCardSum == 18) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;

            }
            //------------------HANDLES SPLITS ACES---------------------------------
        } else if (playerCard1.equals(playerCard2)) {
            switch (dealerCardInt) {
                case 2:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 14 || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 3:
                    if (playerCardSum == 4 || playerCardSum == 6) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 14 || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")
                            || playerCardSum == 12) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 4:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12 || playerCardSum == 14
                            || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 5:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12 || playerCardSum == 14
                            || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 6:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12 || playerCardSum == 14
                            || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 7:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 14 || playerCardSum == 16 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8 || playerCardSum == 12) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 8:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 9:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 10:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 || playerCardSum == 10 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                //Logic is for if a dealer shows an ace but does not reveal a blackjack
                case 11:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 || playerCardSum == 10 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_SPLIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    }
                    break;
            }

        } else {
            //------------------HANDLES all REMAINING CASES---------------------------------
            switch (dealerCardInt) {
                case 2:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 3:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8 ||
                            playerCardSum == 12) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 4:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 5:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 6:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 7:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 ||
                            playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 8:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 ||
                            playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 9:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 ||
                            playerCardSum == 15) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_HIT.name();
                    }
                    break;
                case 10:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 ||
                            playerCardSum == 14 || playerCardSum == 10) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_HIT.name();
                    }
                    break;
                case 11:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 ||
                            playerCardSum == 14 || playerCardSum == 10 ) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 17) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_STAND.name();
                    }
                    break;
            }
        }
        return HandAdvice.SPLIT.name();
	}
	
	private String getHelpForStayOnSoft17(String dealerCard, String playerCard1, String playerCard2) throws BlackjackHelperServiceException {

		int dealerCardInt = 0;
		int playerCardAInt = 0;
		int playerCardBInt = 0;
		int playerCardSum = 0;
		try {
        dealerCardInt = CardValue.fromCardValue(dealerCard);
        playerCardAInt = CardValue.fromCardValue(playerCard1);
        playerCardBInt = CardValue.fromCardValue(playerCard2);
        playerCardSum = playerCardAInt + playerCardBInt;
		} catch (IllegalArgumentException e) {
			throw new BlackjackHelperServiceException(e.getMessage());
		}

		if (playerCardSum == 21) {
		    return HandAdvice.BLACKJACK.name();
        }

        //------------------HANDLES SOFT ACES---------------------------------
        if (playerCard1.equals("A") || playerCard2.equals("A") && playerCard1 != playerCard2) {
            switch (dealerCardInt) {
                case 2:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 ||
                            playerCardSum == 16 || playerCardSum == 17) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 3:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 ||
                            playerCardSum == 16) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 17) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 4:
                    if (playerCardSum == 13 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 15 || playerCardSum == 16 || playerCardSum == 17) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 5:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 18) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 6:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 18 ) {
                        return HandAdvice.DOUBLE_OR_STAND.name();
                    } else if (playerCardSum == 20 || playerCardSum == 19) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 7:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 8:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 9:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 || playerCardSum == 18) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 10:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 || playerCardSum == 18) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 11:
                    if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16 ||
                            playerCardSum == 17 || playerCardSum == 18) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;

            }
            //------------------HANDLES SPLITS ACES---------------------------------
        } else if (playerCard1.equals(playerCard2)) {
            switch (dealerCardInt) {
                case 2:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 14 || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 3:
                    if (playerCardSum == 4 || playerCardSum == 6) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 14 || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")
                            || playerCardSum == 12) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 4:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12 || playerCardSum == 14
                            || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 5:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12 || playerCardSum == 14
                            || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 6:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 12 || playerCardSum == 14
                            || playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8) {
                        return HandAdvice.SPLIT_IF_DOUBLE_ALLOWED_OR_HIT.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 7:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 14 || playerCardSum == 16 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 8 || playerCardSum == 12) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 8:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 9:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16 || playerCardSum == 18 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 10:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 || playerCardSum == 10 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 16 || playerCard1.equals("A")) {
                        return HandAdvice.SPLIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                //Logic is for if a dealer shows an ace but does not reveal a blackjack
                case 11:
                    if (playerCardSum == 4 || playerCardSum == 6 || playerCardSum == 8 || playerCardSum == 10 ||
                            playerCardSum == 12 || playerCardSum == 14) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCard1.equals("A") || playerCardSum == 16) {
                        return HandAdvice.SPLIT.name();
                    }
                    break;
            }

        } else {
            //------------------HANDLES all REMAINING CASES---------------------------------
            switch (dealerCardInt) {
                case 2:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    }
                    break;
                case 3:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8 ||
                            playerCardSum == 12) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 4:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 5:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 6:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 || playerCardSum == 15 || playerCardSum == 16
                            || playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 9 || playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 7:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 ||
                            playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 8:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 ||
                            playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    }
                    break;
                case 9:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14 ||
                            playerCardSum == 15) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 10 || playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_HIT.name();
                    }
                    break;
                case 10:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 ||
                            playerCardSum == 14 || playerCardSum == 10) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 17 || playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 11) {
                        return HandAdvice.DOUBLE_OR_HIT.name();
                    } else if (playerCardSum == 15 || playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_HIT.name();
                    }
                    break;
                case 11:
                    if (playerCardSum == 4 || playerCardSum == 5 || playerCardSum == 6 || playerCardSum == 7 || playerCardSum == 8
                            || playerCardSum == 9 || playerCardSum == 12 || playerCardSum == 13 || playerCardSum == 14
                            || playerCardSum == 15 || playerCardSum == 11 || playerCardSum == 10) {
                        return HandAdvice.HIT.name();
                    } else if (playerCardSum == 18 || playerCardSum == 19 || playerCardSum == 20 || playerCardSum == 17) {
                        return HandAdvice.STAY.name();
                    } else if (playerCardSum == 16) {
                        return HandAdvice.SURRENDER_IF_ALLOWED_OR_HIT.name();
                    }
                    break;
            }

        }
            return HandAdvice.SPLIT.name();
	}

}
