package com.bb.game.player.facade;

import com.bb.game.player.dto.request.CreatePlayerRequestDTO;
import com.bb.game.player.dto.response.PlayerResponseDTO;
import com.bb.game.player.exception.PlayerNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Facade for player logic
 */
@Validated
public interface PlayerFacade {
    /**
     * Creates new player
     *
     * @param createPlayerRequestDTO request DTO
     * @return PlayerResponseDTO
     */
    PlayerResponseDTO createPlayer(@Valid @NotNull CreatePlayerRequestDTO createPlayerRequestDTO);

    /**
     * Retrieves all players
     *
     * @return  List<PlayerResponseDTO>
     */
    List<PlayerResponseDTO> getAllPlayers();

    /**
     * Retrieves specific player by UUID
     * @param uuid
     * @return PlayerResponseDTO
     * @throws PlayerNotFoundException if player with that uuid does not exist
     */
    PlayerResponseDTO getPlayerByUUID(@NotNull String uuid) throws PlayerNotFoundException;
}
