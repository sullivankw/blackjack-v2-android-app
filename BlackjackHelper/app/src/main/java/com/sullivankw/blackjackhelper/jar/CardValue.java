package com.sullivankw.blackjackhelper.jar;

public enum CardValue {
	
	TWO ("two",2),
	THREE ("three", 3),
	FOUR ("four", 4),
	FIVE ("five", 5),
	SIX ("six", 6),
	SEVEN ("seven", 7),
	EIGHT ("eight", 8),
	NINE ("nine", 9),
	TEN ("ten", 10),
	JACK ("jack", 10),
	QUEEN ("queen", 10),
	KING ("king", 10),
	ACE ("ace", 11);
	
    private String card;
    private int numericValue;
    private static String FAILURE_CARD_MSG = " is not valid in a standard deck of cards";
    
    private CardValue(String card, int numericValue) {
    	this.card = card;
        this.numericValue = numericValue;
    }

	public int getNumericValue() {
		return numericValue;
	}
	
	public String getCard() {
		return card;
	}
	
	public static String fromNumericValue(int value) throws IllegalArgumentException {	
		for (CardValue v : CardValue.values()) {
			if (v.getNumericValue() == value) {
				return v.getCard();
			}
		}
		throw new IllegalArgumentException(generateErrorMessage(String.valueOf(value)));
	}
	
	public static int fromCardValue(String card) throws IllegalArgumentException {
		for (CardValue v : CardValue.values()) {
			if (v.name().equalsIgnoreCase(card)) {
				return v.getNumericValue();
			}
		}
		throw new IllegalArgumentException(generateErrorMessage(card));
	}
	
	public static String generateErrorMessage(String value) {
		return value + FAILURE_CARD_MSG;
	}
    
}
