package com.bb.game.tombola.service;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import com.bb.game.tombola.enums.GameState;
import com.bb.game.tombola.model.Tombola;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Service containing methods regarding tombola gameplay
 */
@Validated
public interface TombolaGameplayService {
    /**
     * Adds award to a tombola
     *
     * @param tombola
     * @param award
     * @return Tombola
     */
    Tombola assignAward(@Valid @NotNull Tombola tombola, @Valid @NotNull Award award);

    /**
     * Checks whether the tombola has the predefined number of awards
     *
     * @param tombola
     * @return boolean
     */
    boolean isAwardCapacityReached(@Valid @NotNull Tombola tombola);

    /**
     * Checks whether the tombola has the predefines number of players
     *
     * @param tombola
     * @return boolean
     */
    boolean isPlayerCapacityReached(@Valid @NotNull Tombola tombola);

    /**
     * Adds a player to a tombola
     *
     * @param tombola
     * @param player
     * @return Tombola
     */
    Tombola assignPlayer(@Valid @NotNull Tombola tombola, @Valid @NotNull Player player);

    /**
     * Changes the game state of a tombola
     *
     * @param tombola
     * @param gameState
     * @return Tombola
     */
    Tombola changeGameState(@Valid @NotNull Tombola tombola, @Valid @NotNull GameState gameState);
}
