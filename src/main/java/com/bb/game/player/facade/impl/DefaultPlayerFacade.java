package com.bb.game.player.facade.impl;

import com.bb.game.player.converter.CreatePlayerRequestDTOToPlayerConverter;
import com.bb.game.player.converter.PlayerToPlayerResponseDTOConverter;
import com.bb.game.player.dto.request.CreatePlayerRequestDTO;
import com.bb.game.player.dto.response.PlayerResponseDTO;
import com.bb.game.player.exception.PlayerNotFoundException;
import com.bb.game.player.facade.PlayerFacade;
import com.bb.game.player.model.Player;
import com.bb.game.player.service.PlayerService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultPlayerFacade implements PlayerFacade {
    private final PlayerService playerService;
    private final PlayerToPlayerResponseDTOConverter playerToPlayerResponseDTOConverter;
    private final CreatePlayerRequestDTOToPlayerConverter createPlayerRequestDTOToPlayerConverter;

    public DefaultPlayerFacade(PlayerService playerService, PlayerToPlayerResponseDTOConverter playerResponseDTOConverter,
                               CreatePlayerRequestDTOToPlayerConverter createPlayerRequestDTOToPlayerConverter) {
        this.playerService = playerService;
        this.playerToPlayerResponseDTOConverter = playerResponseDTOConverter;
        this.createPlayerRequestDTOToPlayerConverter = createPlayerRequestDTOToPlayerConverter;
    }

    @Override
    public PlayerResponseDTO createPlayer(CreatePlayerRequestDTO createPlayerRequestDTO) {
        Player player = createPlayerRequestDTOToPlayerConverter.convert(createPlayerRequestDTO);
        Player createdPlayer = playerService.createPlayer(player);
        return playerToPlayerResponseDTOConverter.convert(createdPlayer);
    }

    @Override
    public List<PlayerResponseDTO> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return players.stream().map(playerToPlayerResponseDTOConverter::convert).toList();
    }

    @Override
    public PlayerResponseDTO getPlayerByUUID(String uuid) throws PlayerNotFoundException {
        Player player = playerService.getPlayerByUUID(uuid)
                .orElseThrow(() -> new PlayerNotFoundException(uuid));
        return playerToPlayerResponseDTOConverter.convert(player);
    }
}