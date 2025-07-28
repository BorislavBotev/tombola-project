package com.bb.game.player.repository;

import com.bb.game.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for players entity
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    /**
     *
     * Finds player by UUID
     * @param uuid
     * @return  Optional<Player>
     */
    Optional<Player> findByUuid(String uuid);
}
