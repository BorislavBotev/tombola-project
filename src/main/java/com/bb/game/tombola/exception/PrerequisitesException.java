package com.bb.game.tombola.exception;

public class PrerequisitesException extends Exception {
    private static final String MESSAGE =
            "The right amount of awards and players must be assigned before starting the game";

    public PrerequisitesException() {
        super(MESSAGE);
    }
}
