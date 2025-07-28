package com.bb.game.tombola.exception;

import java.text.MessageFormat;

public class GameErrorException extends Exception {
    private final static String MESSAGE = "Game finished with this {0} amount of awards given, while having {1} awards to be given";

    public GameErrorException(int actualNumber, int expectedNumber){
        super(MessageFormat.format(MESSAGE,  actualNumber, expectedNumber));
    }
}
