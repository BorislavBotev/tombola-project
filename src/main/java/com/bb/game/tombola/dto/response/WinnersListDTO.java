package com.bb.game.tombola.dto.response;

import com.bb.game.award.dto.response.AwardMetadataDTO;
import com.bb.game.player.dto.response.PlayerMetadataDTO;

/**
 * DTO containing award and the winner
 * @param award
 * @param player
 */
public record WinnersListDTO(AwardMetadataDTO award, PlayerMetadataDTO player) {
}
