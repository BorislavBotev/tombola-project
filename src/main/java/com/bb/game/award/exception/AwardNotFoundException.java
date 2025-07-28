package com.bb.game.award.exception;

import java.text.MessageFormat;

public class AwardNotFoundException extends Exception {
    private static final String ERROR_MESSAGE = "Award with UUID: {0} was not found.";

    public AwardNotFoundException(String id) {
        super(MessageFormat.format(ERROR_MESSAGE, id));
    }
}