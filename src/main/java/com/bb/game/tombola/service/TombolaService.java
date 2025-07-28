package com.bb.game.tombola.service;

import com.bb.game.tombola.model.Tombola;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Service for main Tombola operations
 */
@Validated
public interface TombolaService {
    /**
     * Creates Tombola
     *
     * @param tombola
     * @return Tombola
     */
    Tombola createTombola(@Valid @NotNull Tombola tombola);

    /**
     * Returns all tombolas
     *
     * @return List<Tombola>
     */
    List<Tombola> getAllTombolas();

    /**
     * Returns tombolas based on UUID
     *
     * @param uuid
     * @return Optional<Tombola>
     */
    Optional<Tombola> getTombolaByUUID(@NotNull String uuid);
}
