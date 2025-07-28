package com.bb.game.player.service;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Service for player logic
 */
@Validated
public interface PlayerService {
    /**
     * Creates new player based on Player object
     *
     * @param player Player
     * @return Player
     */
    Player createPlayer(@Valid @NotNull Player player);

    /**
     * Retrieves all players
     *
     * @return List<Player>
     */
    List<Player> getAllPlayers();

    /**
     * Retrieves player by UUID
     *
     * @param uuid
     * @return Optional<Player>
     */
    Optional<Player> getPlayerByUUID(@NotNull String uuid);

    /**
     * Add award to the players list of awards
     * @param player
     * @param award
     * @return
     */
    Player addAward(@Valid @NotNull Player player, @Valid @NotNull Award award);
}
