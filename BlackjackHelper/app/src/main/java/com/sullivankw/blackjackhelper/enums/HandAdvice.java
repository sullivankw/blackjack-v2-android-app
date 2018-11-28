package com.sullivankw.blackjackhelper.enums;

import java.util.ArrayList;
import java.util.List;

public enum HandAdvice {
    HIT("HIT"),
    STAY("STAY"),
    SPLIT("SPLIT"),
    SPLIT_IF_DOUBLE_ALLOWED_OR_HIT("SPLIT IF DOUBLE ALLOWED OR HIT"),
    SURRENDER_IF_ALLOWED_OR_SPLIT("SURRENDER IF ALLOWED OR SPLIT"),
    SURRENDER_IF_ALLOWED_OR_HIT("SURRENDER IF ALLOWED OR HIT"),
    SURRENDER_IF_ALLOWED_OR_STAND("SURRENDER IF ALLOWED OR STAND"),
    DOUBLE_OR_HIT("DOUBLE IF ALLOWED OR HIT"),
    DOUBLE_OR_STAND("DOUBLE IF ALLOWED OR STAND"),
    BLACKJACK("BLACKJACK");

    private String displayValue;

    HandAdvice(String displayValue) {
        this.displayValue = displayValue;
    }

    public static String fromEnum(String value) throws IllegalArgumentException {
        for (HandAdvice advice : HandAdvice.values()) {
            if (advice.name().equals(value)) {
                return advice.getDisplayValue();
            }
        }
        throw new IllegalArgumentException();
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static List<String> getDisplayValues() {
        List<String> displayValues = new ArrayList<>();
        for (HandAdvice advice : HandAdvice.values()) {
            String displayValue = advice.getDisplayValue();
            displayValues.add(displayValue);
        }
        return displayValues;
    }
}
