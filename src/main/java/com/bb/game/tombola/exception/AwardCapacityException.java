package com.bb.game.tombola.exception;

import java.text.MessageFormat;

public class AwardCapacityException extends Exception {
    private static final String MESSAGE = "The maximum amount - {0} of awards has been reached for this game";

    public AwardCapacityException(int awardsCount) {
        super(MessageFormat.format(MESSAGE, awardsCount));
    }
}
