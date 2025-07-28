package com.bb.game.award.converter;

import com.bb.game.award.dto.response.AwardResponseDTO;
import com.bb.game.award.model.Award;
import com.bb.game.player.converter.PlayerToMetadataDTOConverter;
import com.bb.game.player.dto.response.PlayerMetadataDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts Award to appropriate Award Response DTO containing all the data
 */
@Component
public class AwardToAwardResponseDTOConverter implements Converter<Award, AwardResponseDTO> {
    private final PlayerToMetadataDTOConverter playerToMetadataDTOConverter;

    public AwardToAwardResponseDTOConverter(PlayerToMetadataDTOConverter  playerToMetadataDTOConverter) {
        this.playerToMetadataDTOConverter = playerToMetadataDTOConverter;
    }

    @Override
    public AwardResponseDTO convert(Award source) {
        PlayerMetadataDTO playerMetadataDTO = playerToMetadataDTOConverter.convert(source.getPlayer());
        return new AwardResponseDTO(source.getUuid(), source.getName(), playerMetadataDTO);
    }
}
