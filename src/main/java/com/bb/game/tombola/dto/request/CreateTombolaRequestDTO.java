package com.bb.game.tombola.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Create Request Tombola DTO
 * @param name
 * @param maxPlayers
 * @param maxAwards
 */
public record CreateTombolaRequestDTO(@NotBlank(message = "Name must not be empty") String name,
                                      @Positive(message = "Player capacity must be positive") int maxPlayers,
                                      @Positive(message = "Award capacity must be positive") int maxAwards) {
}
