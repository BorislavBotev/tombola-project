package com.bb.game.player.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

/**
 * DTO used to obtain the data needed from the request body to create player
 *
 * @param name
 * @param email
 * @param weight
 */
@Validated
public record CreatePlayerRequestDTO(
        @NotBlank(message = "Name must not be empty") String name,
        @Email(message = "Invalid email format")
        String email,
        @PositiveOrZero(message = "Weight must be positive number") int weight) {
}
