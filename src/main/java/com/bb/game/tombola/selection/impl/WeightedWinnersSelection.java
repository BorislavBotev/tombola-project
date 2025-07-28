package com.bb.game.tombola.selection.impl;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import com.bb.game.tombola.selection.WinnersSelectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Strategy related to WEIGHTED
 */
@Component("WEIGHTED")
public class WeightedWinnersSelection implements WinnersSelectionStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeightedWinnersSelection.class);

    private static final String SELECTING_WINNERS_MESSAGE= "Selecting Winners based on WEIGHTED approach - players with more weight have higher chance";
    private static final String NO_WEIGHT_MESSAGE = "Players have no weight for the weighted selection method, no awards will be given";

    /**
     * For every award decides a winner, players with higher weight has bigger chance
     *
     * @param players all the players in the tombola
     * @param awards all the awards in the tombola
     * @return Map<Award, Player>
     */
    @Override
    public Map<Award, Player> selectWinners(List<Player> players, List<Award> awards) {
        LOGGER.info(SELECTING_WINNERS_MESSAGE);

        int totalWeight = players.stream()
                .mapToInt(Player::getWeight).sum();

        if (totalWeight == 0) {
            LOGGER.warn(NO_WEIGHT_MESSAGE);
            return Collections.emptyMap();
        }

        return giveAwards(awards, players, totalWeight);
    }

    private Map<Award, Player> giveAwards(List<Award> awards, List<Player> players, int totalWeight) {
        Map<Award, Player> winners = new HashMap<>();
        Random random = new Random();

        for (Award award : awards) {
            int randomValue = random.nextInt(totalWeight);
            int cumulative = 0;

            for (Player player : players) {
                cumulative += player.getWeight();
                if (randomValue <= cumulative) {
                    winners.put(award, player);
                    break;
                }
            }
        }
        return winners;
    }
}
