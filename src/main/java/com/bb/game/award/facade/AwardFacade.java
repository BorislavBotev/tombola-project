package com.bb.game.award.facade;

import com.bb.game.award.dto.request.CreateAwardRequestDTO;
import com.bb.game.award.dto.response.AwardResponseDTO;
import com.bb.game.award.exception.AwardNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Facade for the Award object
 */
@Validated
public interface AwardFacade {
    /**
     * Creates new Award
     *
     * @param createAwardRequestDTO
     * @return AwardResponseDTO
     */
    AwardResponseDTO createAward(@Valid @NotNull CreateAwardRequestDTO createAwardRequestDTO);

    /**
     * Returns Award found by UUID
     *
     * @param uuid
     * @return AwardResponseDTO
     * @throws AwardNotFoundException
     */
    AwardResponseDTO getAwardByUUID(@NotNull String uuid) throws AwardNotFoundException;

    /**
     * Retrieves all awards
     *
     * @return List<AwardResponseDTO>
     */
    List<AwardResponseDTO> getAllAwards();
}
