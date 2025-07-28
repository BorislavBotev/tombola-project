package com.bb.game.tombola.selection.impl;

import com.bb.game.tombola.enums.WinnersSelection;
import com.bb.game.tombola.exception.WinnersSelectionException;
import com.bb.game.tombola.selection.WinnersSelectionStrategy;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Factory for winning strategy selection
 */
@Component
public class WinnersSelectionFactory {
    public static final String STRATEGY_MESSAGE = "No strategy found for: {0}";

    private final Map<String, WinnersSelectionStrategy> strategies;

    public WinnersSelectionFactory(Map<String, WinnersSelectionStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Based on enum type returns the roeect strategy class
     *
     * @param type
     * @return WinnersSelectionStrategy
     * @throws WinnersSelectionException thrown when strategy cant be chosen
     */
    public WinnersSelectionStrategy getStrategy(WinnersSelection type) throws WinnersSelectionException {
        WinnersSelectionStrategy strategy = strategies.get(type.name());
        if (strategy == null) {
            throw new WinnersSelectionException(MessageFormat.format(STRATEGY_MESSAGE, type));
        }
        return strategy;
    }
}
