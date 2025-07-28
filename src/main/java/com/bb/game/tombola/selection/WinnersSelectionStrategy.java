package com.bb.game.tombola.selection;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

public interface WinnersSelectionStrategy {
    Map<Award, Player> selectWinners(@Valid @NotEmpty List<Player> players, @Valid @NotEmpty List<Award> awards);
}
