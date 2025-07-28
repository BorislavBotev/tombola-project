package com.bb.game.player.controller;

import com.bb.game.player.dto.request.CreatePlayerRequestDTO;
import com.bb.game.player.dto.response.PlayerResponseDTO;
import com.bb.game.player.exception.PlayerNotFoundException;
import com.bb.game.player.facade.PlayerFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/players")
@Tag(name = "Player APIs")
/**
 * Controller responsible for the player endpoints
 */
public class PlayerController {
    private final PlayerFacade playerFacade;

    PlayerController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    /**
     * Post request for creating a new player
     *
     * @param createPlayerRequestDTO contains the necessary data
     * @return ResponseEntity<PlayerResponseDTO>
     */
    @PostMapping()
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @NotNull
                                                          @RequestBody
                                                          CreatePlayerRequestDTO createPlayerRequestDTO) {
        PlayerResponseDTO createdPlayer = playerFacade.createPlayer(createPlayerRequestDTO);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    /**
     * Get request for retrieving all players
     *
     * @return ResponseEntity<List < PlayerResponseDTO>>
     */
    @GetMapping()
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        List<PlayerResponseDTO> players = playerFacade.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    /**
     * Get request for retrieving a specific player
     *
     * @param uuid unique identifier
     * @return ResponseEntity<PlayerResponseDTO>
     * @throws PlayerNotFoundException thrown when player with that uuid does not exist in the DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayerByUUID(@PathVariable("id") String uuid) throws PlayerNotFoundException {
        PlayerResponseDTO playerResponseDTO = playerFacade.getPlayerByUUID(uuid);
        return new ResponseEntity<>(playerResponseDTO, HttpStatus.OK);
    }
}