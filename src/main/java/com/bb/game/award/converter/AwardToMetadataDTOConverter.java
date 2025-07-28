package com.bb.game.award.converter;

import com.bb.game.award.dto.response.AwardMetadataDTO;
import com.bb.game.award.model.Award;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts Award to Metadata DTO containing only the basic Award fields
 */
@Component
public class AwardToMetadataDTOConverter implements Converter<Award, AwardMetadataDTO> {

    @Override
    public AwardMetadataDTO convert(Award source) {
        return new AwardMetadataDTO(source.getUuid(), source.getName());
    }
}
