package com.bb.game.player.converter;

import com.bb.game.player.dto.request.CreatePlayerRequestDTO;
import com.bb.game.player.model.Player;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts the provided create player request DTO to player object
 */
@Component
public class CreatePlayerRequestDTOToPlayerConverter implements Converter<CreatePlayerRequestDTO, Player> {
    @Override
    public Player convert(CreatePlayerRequestDTO source) {
        return new Player(source.name(), source.email(), source.weight());
    }
}
