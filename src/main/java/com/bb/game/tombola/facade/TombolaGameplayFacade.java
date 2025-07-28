package com.bb.game.tombola.facade;

import com.bb.game.award.exception.AwardNotFoundException;
import com.bb.game.player.exception.PlayerNotFoundException;
import com.bb.game.tombola.dto.request.IDRequestDTO;
import com.bb.game.tombola.dto.request.WinnersSelectionIDDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.dto.response.TombolaWinnersDTO;
import com.bb.game.tombola.exception.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * Facade regarding the gameplay operations
 */
@Validated
public interface TombolaGameplayFacade {
    /**
     * Assigns an award to a tombola
     *
     * @param tombolaUUID
     * @param awardIDRequestDTO
     * @return TombolaResponseDTO
     * @throws AwardNotFoundException thrown when Award with that uuid does not exist
     * @throws TombolaNotFoundException thrown when Tombola with that uuid does not exist
     * @throws AwardCapacityException thrown when the max number of awards have been signed
     * @throws AwardAvailabilityException thrown when Award can not be signed to tombola
     */
    TombolaResponseDTO assignAward(@NotNull String tombolaUUID, @Valid @NotNull IDRequestDTO awardIDRequestDTO) throws AwardNotFoundException, TombolaNotFoundException, AwardCapacityException, AwardAvailabilityException;

    /**
     * Assigns a player to a tombola
     *
     * @param tombolaUUID
     * @param playerIDRequestDTO
     * @return TombolaResponseDTO
     * @throws TombolaNotFoundException thrown when Tombola with that uuid does not exist
     * @throws PlayerCapacityException thrown when the max number of players have been signed
     * @throws PlayerNotFoundException thrown when Player with that uuid does not exist
     */
    TombolaResponseDTO assignPlayer(@NotNull String tombolaUUID, @Valid @NotNull IDRequestDTO playerIDRequestDTO) throws TombolaNotFoundException, PlayerCapacityException, PlayerNotFoundException;

    /**
     * Takes the tombola game into action - plays the game
     *
     * @param tombolaUUID
     * @param winnersSelectionIDDTO
     * @return TombolaWinnersDTO
     * @throws TombolaNotFoundException thrown when Tombola with that uuid does not exist
     * @throws PrerequisitesException thrown when the needed number of players/awards haven't been fulfilled
     * @throws GameErrorException thrown when not all assigned awards have been given
     * @throws GameStateException thrown when tombola is not in a beginning state - it was started before
     * @throws WinnersSelectionException thrown when can not decide a selection strategy
     */
    TombolaWinnersDTO play(@NotNull String tombolaUUID, @Valid @NotNull WinnersSelectionIDDTO winnersSelectionIDDTO) throws TombolaNotFoundException, PrerequisitesException, GameErrorException, GameStateException, WinnersSelectionException;
}