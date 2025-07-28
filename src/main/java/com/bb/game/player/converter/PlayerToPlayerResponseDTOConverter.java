package com.bb.game.player.converter;

import com.bb.game.award.converter.AwardToAwardResponseDTOConverter;
import com.bb.game.award.converter.AwardToMetadataDTOConverter;
import com.bb.game.award.dto.response.AwardMetadataDTO;
import com.bb.game.award.dto.response.AwardResponseDTO;
import com.bb.game.player.dto.response.PlayerResponseDTO;
import com.bb.game.player.model.Player;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Converts player object to a valid player response DTO
 */
@Component
public class PlayerToPlayerResponseDTOConverter implements Converter<Player, PlayerResponseDTO> {
    private final AwardToMetadataDTOConverter awardToMetadataDTOConverter;

    public PlayerToPlayerResponseDTOConverter( AwardToMetadataDTOConverter awardToMetadataDTOConverter) {
        this.awardToMetadataDTOConverter = awardToMetadataDTOConverter;
    }

    @Override
    public PlayerResponseDTO convert(Player source) {
        List<AwardMetadataDTO> awards = Optional.ofNullable(source.getAwards())
                .orElse(Collections.emptyList())
                .stream().map(this.awardToMetadataDTOConverter::convert).toList();
        return new PlayerResponseDTO(source.getUuid(), source.getName(), source.getEmail(), source.getWeight(), awards);
    }
}
