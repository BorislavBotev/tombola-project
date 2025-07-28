package com.bb.game.tombola.converter;

import com.bb.game.award.converter.AwardToMetadataDTOConverter;
import com.bb.game.award.dto.response.AwardMetadataDTO;
import com.bb.game.player.converter.PlayerToMetadataDTOConverter;
import com.bb.game.player.dto.response.PlayerMetadataDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.model.Tombola;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Converts Tombola object to a Tombola response DTO with all data
 */
@Component
public class TombolaToTombolaResponseDTOConverter implements Converter<Tombola, TombolaResponseDTO> {
    private final PlayerToMetadataDTOConverter playerToMetadataDTOConverter;
    private final AwardToMetadataDTOConverter awardToMetadataDTOConverter;

    public TombolaToTombolaResponseDTOConverter(PlayerToMetadataDTOConverter playerToMetadataDTOConverter,
                                                AwardToMetadataDTOConverter awardToMetadataDTOConverter) {
        this.playerToMetadataDTOConverter = playerToMetadataDTOConverter;
        this.awardToMetadataDTOConverter = awardToMetadataDTOConverter;
    }


    @Override
    public TombolaResponseDTO convert(Tombola source) {

        List<AwardMetadataDTO> awards = Optional.ofNullable(source.getAwards())
                .orElse(Collections.emptyList()).stream().map(awardToMetadataDTOConverter::convert).toList();
        List<PlayerMetadataDTO> players = Optional.ofNullable(source.getPlayers())
                .orElse(Collections.emptyList()).stream().map(playerToMetadataDTOConverter::convert).collect(Collectors.toList());

        return new TombolaResponseDTO(source.getUuid(), source.getName(), source.getGameState(), source.getMaxPlayers(), source.getMaxAwards(), players, awards);
    }
}
