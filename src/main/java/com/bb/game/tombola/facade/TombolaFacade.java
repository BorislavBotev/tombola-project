package com.bb.game.tombola.facade;

import com.bb.game.tombola.dto.request.CreateTombolaRequestDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.exception.TombolaNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Facade for the main operations for Tombola
 */
@Validated
public interface TombolaFacade {
    /**
     * Creates new tombola
     *
     * @param tombola
     * @return TombolaResponseDTO
     */
    TombolaResponseDTO createTombola(@Valid @NotNull CreateTombolaRequestDTO tombola);

    /**
     * Returns all tombolas from the DB
     *
     * @return List<TombolaResponseDTO>
     */
    List<TombolaResponseDTO> getAllTombolas();

    /**
     * Returns a specific tombola based on UUID
     *
     * @param uuid
     * @return TombolaResponseDTO
     * @throws TombolaNotFoundException thrown when tombola with that UUID does not exist
     */
    TombolaResponseDTO getTombolaByUUID(@NotNull String uuid) throws TombolaNotFoundException;
}
