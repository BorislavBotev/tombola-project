package com.bb.game.player.dto.response;

import com.bb.game.award.dto.response.AwardMetadataDTO;

import java.util.List;

/**
 * Player response DTO containing full information for the object
 *
 * @param uuid
 * @param name
 * @param email
 * @param weight
 * @param awards
 */
public record PlayerResponseDTO(String uuid, String name, String email, int weight, List<AwardMetadataDTO> awards) {
}
