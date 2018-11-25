package com.sullivankw.blackjackhelper;

public enum HandAdvice {
    HIT,
    STAY,
    SPLIT,
    SPLIT_IF_DOUBLE_ALLOWED_OR_HIT,
    SURRENDER_IF_ALLOWED_OR_SPLIT,
    SURRENDER_IF_ALLOWED_OR_HIT,
    SURRENDER_IF_ALLOWED_OR_STAND,
    DOUBLE_OR_HIT,
    DOUBLE_OR_STAND,
    BLACKJACK,
    PLACEHOLDER;

    public static String fromEnum(String value) throws IllegalArgumentException {
        for (HandAdvice v : HandAdvice.values()) {
            if (v.name().equals(value)) {
                String strippedValue = value.replace("_", " ");
                return strippedValue;
            }
        }
        throw new IllegalArgumentException();
    }
}
