package com.bb.game.award.dto.response;

import com.bb.game.player.dto.response.PlayerMetadataDTO;

/**
 * DTO containing all the needed information for an award object
 *
 * @param uuid
 * @param name
 * @param player
 */
public record AwardResponseDTO(String uuid, String name, PlayerMetadataDTO player) {
}
