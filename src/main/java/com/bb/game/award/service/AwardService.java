package com.bb.game.award.service;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Service for the Award logic
 */
@Validated
public interface AwardService {
    /**
     * Creates new Award
     *
     * @param award
     * @return Award
     */
    Award createAward(@Valid @NotNull Award award);

    /**
     * Retrieves Award by UUID
     *
     * @param uuid
     * @return Optional<Award>
     */
    Optional<Award> getAwardByUUID(@NotNull String uuid);

    /**
     * Retrieves all Awards
     *
     * @return List<Award>
     */
    List<Award> getAllAwards();

    Award setPlayer(@Valid @NotNull Award award, @Valid @NotNull Player player);
}
