package com.bb.game.tombola.selection.impl;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import com.bb.game.tombola.selection.WinnersSelectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Strategy related to RANDOM
 */
@Component("RANDOM")
public class RandomWinnersSelection implements WinnersSelectionStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomWinnersSelection.class);

    private static final String SELECTING_WINNERS_MESSAGE= "Selecting Winners based on BASIC approach - fully random";

    /**
     * Purely on random for every award decides a winner
     *
     * @param players all the players in the tombola
     * @param awards all the awards in the tombola
     * @return Map<Award, Player>
     */
    @Override
    public Map<Award, Player> selectWinners(List<Player> players, List<Award> awards) {
        LOGGER.info(SELECTING_WINNERS_MESSAGE);
        
        Map<Award, Player> winners = new HashMap<>();
        Random random = new Random();

        for (Award award : awards) {
            Player randomPlayer = players.get(random.nextInt(players.size()));
            winners.put(award, randomPlayer);
        }

        return winners;
    }
}
