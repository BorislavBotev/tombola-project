package com.bb.game.tombola.exception;

import java.text.MessageFormat;

public class PlayerCapacityException extends Exception {
    private static final String MESSAGE = "The maximum amount - {0} of players for this game has been reached ";

    public PlayerCapacityException(long capacity) {
        super(MessageFormat.format(MESSAGE, capacity));
    }
}
