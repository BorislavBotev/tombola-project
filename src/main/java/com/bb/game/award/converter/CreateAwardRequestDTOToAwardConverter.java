package com.bb.game.award.converter;

import com.bb.game.award.dto.request.CreateAwardRequestDTO;
import com.bb.game.award.model.Award;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts the create award request DTO to an Award object
 */
@Component
public class CreateAwardRequestDTOToAwardConverter implements Converter<CreateAwardRequestDTO, Award> {

    @Override
    public Award convert(CreateAwardRequestDTO source) {
        return new Award(source.name());
    }
}
