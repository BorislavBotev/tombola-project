package com.bb.game.award.repository;

import com.bb.game.award.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for the Award entity
 */
@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
    /**
     * Returns optional Award found by UUID
     *
     * @param uuid
     * @return Optional<Award>
     */
    Optional<Award> findByUuid(String uuid);
}
