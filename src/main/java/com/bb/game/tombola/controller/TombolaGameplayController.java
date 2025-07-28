package com.bb.game.tombola.controller;

import com.bb.game.award.exception.AwardNotFoundException;
import com.bb.game.player.exception.PlayerNotFoundException;
import com.bb.game.tombola.dto.request.IDRequestDTO;
import com.bb.game.tombola.dto.request.WinnersSelectionIDDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.dto.response.TombolaWinnersDTO;
import com.bb.game.tombola.exception.*;
import com.bb.game.tombola.facade.TombolaGameplayFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller specific for gameplay operations
 */
@RestController
@Validated
@RequestMapping("/tombolas")
@Tag(name = "Tombola APIs")
public class TombolaGameplayController {
    private final TombolaGameplayFacade tombolaGameplayFacade;

    TombolaGameplayController(TombolaGameplayFacade tombolaGameplayFacade) {
        this.tombolaGameplayFacade = tombolaGameplayFacade;
    }

    /**
     * Patch request which adds an award to a tombola if it is allowed
     *
     * @param idRequestDTO DTO containing the UUID of the award
     * @param tombolaUUID  UUID of the tombola
     * @return ResponseEntity<TombolaResponseDTO>
     * @throws AwardNotFoundException     thrown when Award with the UUID does not exist
     * @throws TombolaNotFoundException   thrown when Tombola with the UUID does not exist
     * @throws AwardCapacityException     thrown when the Award limit of the Tombola has been reached
     * @throws AwardAvailabilityException thrown when Award is already connected to a tombola
     */
    @PatchMapping("/{tombolaID}/awards")
    public ResponseEntity<TombolaResponseDTO> assignReward(@Valid @NotNull @RequestBody IDRequestDTO idRequestDTO, @PathVariable("tombolaID") String tombolaUUID)
            throws AwardNotFoundException, TombolaNotFoundException, AwardCapacityException, AwardAvailabilityException {
        TombolaResponseDTO responseDTO = tombolaGameplayFacade.assignAward(tombolaUUID, idRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Patch request which adds a player to a tombola if it is allowed
     *
     * @param idRequestDTO DTO containing the UUID of the player
     * @param tombolaUUID  UUID of the tombola
     * @return ResponseEntity<TombolaResponseDTO>
     * @throws TombolaNotFoundException thrown when Tombola with the UUID does not exist
     * @throws PlayerCapacityException  thrown when the Player limit of the Tombola has been reached
     * @throws PlayerNotFoundException  thrown when Player with the UUID does not exist
     */
    @PatchMapping("/{tombolaID}/players")
    public ResponseEntity<TombolaResponseDTO> assignPlayer(@Valid @NotNull @RequestBody IDRequestDTO idRequestDTO, @PathVariable("tombolaID") String tombolaUUID)
            throws TombolaNotFoundException, PlayerCapacityException, PlayerNotFoundException {
        TombolaResponseDTO responseDTO = tombolaGameplayFacade.assignPlayer(tombolaUUID, idRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Patch Request that starts the tombola game if all prerequisites are met
     *
     * @param idDTO       ID of a winners selection strategy with which we can choose how to decide winners
     * @param tombolaUUID UUID of the tombola
     * @return ResponseEntity<TombolaWinnersDTO>
     * @throws GameErrorException       thrown when not all assigned awards have been given
     * @throws PrerequisitesException   thrown when not the needed amount of players/awards are assigned
     * @throws TombolaNotFoundException thrown when Tombola with the UUID does not exist
     * @throws GameStateException       thrown when tombola is not in a beginning state
     */
    @PatchMapping("/{tombolaID}/play")
    public ResponseEntity<TombolaWinnersDTO> playTombola(@Valid @NotNull @RequestBody WinnersSelectionIDDTO idDTO, @PathVariable("tombolaID") String tombolaUUID)
            throws GameErrorException, PrerequisitesException, TombolaNotFoundException, GameStateException, WinnersSelectionException {
        TombolaWinnersDTO winners = tombolaGameplayFacade.play(tombolaUUID, idDTO);
        return new ResponseEntity<>(winners, HttpStatus.OK);
    }
}
