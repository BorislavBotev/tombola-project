package com.bb.game.tombola.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum respective to the winners strategies
 */
public enum WinnersSelection {
    BASIC(1),
    WEIGHTED(2);

    private final int id;

    WinnersSelection(int id) {
        this.id = id;
    }

    public static WinnersSelection getWinnersSelectionBasedOnID(int id) {
        Optional<WinnersSelection> result = Arrays.stream(WinnersSelection.values()).filter(
                selection -> selection.id == id).findFirst();
        return result.orElse(BASIC);
    }
}
