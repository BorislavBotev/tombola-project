package com.bb.game.player.converter;

import com.bb.game.player.dto.response.PlayerMetadataDTO;
import com.bb.game.player.model.Player;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Converts Player object to Player Metadata response DTO.
 * No transitive object data.
 */
@Component
public class PlayerToMetadataDTOConverter implements Converter<Player, PlayerMetadataDTO> {
    @Override
    public PlayerMetadataDTO convert(Player source) {
        return Objects.isNull(source) ? null : new PlayerMetadataDTO(source.getUuid(), source.getName());
    }
}
