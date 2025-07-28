package com.bb.game.player.exception;

import java.text.MessageFormat;

public class PlayerNotFoundException extends Exception {
    private static final String MESSAGE = "Player with UUID: {0} was not found.";

    public PlayerNotFoundException(String message) {
        super(MessageFormat.format(MESSAGE, message));
    }
}