package com.bb.game.tombola.service.impl;

import com.bb.game.tombola.model.Tombola;
import com.bb.game.tombola.repository.TombolaRepository;
import com.bb.game.tombola.service.TombolaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultTombolaService implements TombolaService {
    private final TombolaRepository tombolaRepository;

    DefaultTombolaService(TombolaRepository tombolaRepository) {
        this.tombolaRepository = tombolaRepository;
    }

    @Override
    public Tombola createTombola(Tombola tombola) {
        return tombolaRepository.save(tombola);
    }

    @Override
    public List<Tombola> getAllTombolas() {
        return tombolaRepository.findAll();
    }

    @Override
    public Optional<Tombola> getTombolaByUUID(String uuid) {
        return tombolaRepository.findByUuid(uuid);
    }
}
