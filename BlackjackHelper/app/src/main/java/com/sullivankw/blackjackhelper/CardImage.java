package com.sullivankw.blackjackhelper;

public enum CardImage {

    TWO(R.drawable.ic_2_of_clubs, 0),
    THREE(R.drawable.ic_3_of_clubs, 1),
    FOUR(R.drawable.ic_4_of_clubs, 2),
    FIVE(R.drawable.ic_5_of_clubs, 3),
    SIX(R.drawable.ic_6_of_clubs, 4),
    SEVEN(R.drawable.ic_7_of_clubs, 5),
    EIGHT(R.drawable.ic_8_of_clubs, 6),
    NINE(R.drawable.ic_9_of_clubs, 7),
    TEN(R.drawable.ic_10_of_clubs, 8),
    JACK(R.drawable.ic_jack_of_clubs, 9),
    QUEEN(R.drawable.ic_queen_of_clubs, 10),
    KING(R.drawable.ic_king_of_clubs, 11),
    ACE(R.drawable.ic_ace_of_clubs, 12);

    private int resourceId;
    private int position;


    CardImage(int resourceId, int position) {
        this.resourceId = resourceId;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getResourceId() {
        return resourceId;
    }

    public static int getResourceIdByPosition(int position) throws IllegalArgumentException{
        for (CardImage cardImage : CardImage.values()) {
            if (cardImage.getPosition() == position) {
                return cardImage.getResourceId();
            }
        }
        throw new IllegalArgumentException();
    }
}
