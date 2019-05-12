package com.epam.app.model.enums;

public enum CardState {

    ORDERED,
    AT_HOME,
    AT_HALL,
    RETURNED,
    OVERDUE;

    public static CardState getCardState(int CardStateId) {
        switch (CardStateId) {
            case 1:
                return ORDERED;
            case 2:
                return AT_HOME;
            case 3:
                return AT_HALL;
            case 4:
                return RETURNED;
            case 5:
                return OVERDUE;
            default:
                throw new IllegalArgumentException("Argument must be in a range of 1 to 5");
        }
    }

    public static int getPriority(CardState cardState) {
        switch (cardState) {
            case OVERDUE:
                return 1;
            case ORDERED:
                return 2;
            case AT_HALL:
                return 3;
            case AT_HOME:
                return 4;
            case RETURNED:
                return 5;
            default:
                throw new IllegalArgumentException("Argument must be in a range of 1 to 5");
        }
    }
}

