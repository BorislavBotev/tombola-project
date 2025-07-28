package com.bb.game.tombola.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * DTO containing uuid
 * @param uuid
 */
public record IDRequestDTO(@NotNull String uuid) {
}
