package com.bb.game.tombola.exception;

import java.text.MessageFormat;

public class TombolaNotFoundException  extends Exception {
    private static final String ERROR_MESSAGE = "Tombola with UUID: {0} was not found.";

    public TombolaNotFoundException(String id) {
        super(MessageFormat.format(ERROR_MESSAGE, id));
    }
}
