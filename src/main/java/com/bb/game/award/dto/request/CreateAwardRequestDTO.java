package com.bb.game.award.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO used to obtain the body information
 *
 * @param name
 */
public record CreateAwardRequestDTO(@NotBlank (message = "Name must not be empty") String name) {
}
