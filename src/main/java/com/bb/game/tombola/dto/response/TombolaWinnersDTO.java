package com.bb.game.tombola.dto.response;

import java.util.List;

/**
 * DTO with endgame data
 *
 * @param description
 * @param winnersList
 */
public record TombolaWinnersDTO(String description, List<WinnersListDTO> winnersList) {
}
