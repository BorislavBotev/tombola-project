package com.bb.game.tombola.facade.impl;

import com.bb.game.tombola.converter.CreateTombolaRequestToTombolaConverter;
import com.bb.game.tombola.converter.TombolaToTombolaResponseDTOConverter;
import com.bb.game.tombola.dto.request.CreateTombolaRequestDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.exception.TombolaNotFoundException;
import com.bb.game.tombola.facade.TombolaFacade;
import com.bb.game.tombola.model.Tombola;
import com.bb.game.tombola.service.TombolaService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultTombolaFacade implements TombolaFacade {
    private final TombolaService tombolaService;
    private final TombolaToTombolaResponseDTOConverter tombolaToTombolaResponseDTOConverter;
    private final CreateTombolaRequestToTombolaConverter createTombolaRequestToTombolaConverter;


    DefaultTombolaFacade(TombolaService tombolaService,
                         TombolaToTombolaResponseDTOConverter converter, CreateTombolaRequestToTombolaConverter createTombolaRequestToTombolaConverter) {
        this.tombolaService = tombolaService;
        this.tombolaToTombolaResponseDTOConverter = converter;
        this.createTombolaRequestToTombolaConverter = createTombolaRequestToTombolaConverter;
    }

    @Override
    public TombolaResponseDTO createTombola(CreateTombolaRequestDTO createTombolaRequestDTO) {
        Tombola tombola = createTombolaRequestToTombolaConverter.convert(createTombolaRequestDTO);
        Tombola createdTombola = tombolaService.createTombola(tombola);
        return tombolaToTombolaResponseDTOConverter.convert(createdTombola);
    }

    @Override
    public List<TombolaResponseDTO> getAllTombolas() {
        List<Tombola> tombolas = tombolaService.getAllTombolas();
        return tombolas.stream().map(tombolaToTombolaResponseDTOConverter::convert).toList();
    }

    @Override
    public TombolaResponseDTO getTombolaByUUID(String uuid) throws TombolaNotFoundException {
        Tombola tombola = tombolaService.getTombolaByUUID(uuid)
                .orElseThrow(() -> new TombolaNotFoundException(uuid));
        return tombolaToTombolaResponseDTOConverter.convert(tombola);
    }
}