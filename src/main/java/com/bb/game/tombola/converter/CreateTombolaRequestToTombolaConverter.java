package com.bb.game.tombola.converter;

import com.bb.game.tombola.dto.request.CreateTombolaRequestDTO;
import com.bb.game.tombola.model.Tombola;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts Request Tombola DTO to a Tombola object
 */
@Component
public class CreateTombolaRequestToTombolaConverter implements Converter<CreateTombolaRequestDTO, Tombola> {
    @Override
    public Tombola convert(CreateTombolaRequestDTO source) {
        return new Tombola(source.name(), source.maxPlayers(), source.maxAwards());
    }
}
