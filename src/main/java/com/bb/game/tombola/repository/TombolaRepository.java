package com.bb.game.tombola.repository;

import com.bb.game.tombola.model.Tombola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Tombola entity
 */
@Repository
public interface TombolaRepository extends JpaRepository<Tombola, Long> {
    /**
     * Return Tombola by UUID
     *
     * @param uuid
     * @return Optional<Tombola>
     */
    Optional<Tombola> findByUuid(String uuid);
}