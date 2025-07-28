package com.bb.game.tombola.dto.response;

import com.bb.game.award.dto.response.AwardMetadataDTO;
import com.bb.game.player.dto.response.PlayerMetadataDTO;
import com.bb.game.tombola.enums.GameState;

import java.util.List;

/**
 * Tombola response DTO with all data
 * @param uuid
 * @param name
 * @param gameState
 * @param maxPlayers
 * @param maxAwards
 * @param players
 * @param awards
 */
public record TombolaResponseDTO(String uuid, String name, GameState gameState, int maxPlayers, int maxAwards,
                                 List<PlayerMetadataDTO> players, List<AwardMetadataDTO> awards) {
}
