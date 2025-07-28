package com.bb.game.tombola.service.impl;

import com.bb.game.award.model.Award;
import com.bb.game.award.repository.AwardRepository;
import com.bb.game.player.model.Player;
import com.bb.game.tombola.enums.GameState;
import com.bb.game.tombola.model.Tombola;
import com.bb.game.tombola.repository.TombolaRepository;
import com.bb.game.tombola.service.TombolaGameplayService;
import org.springframework.stereotype.Service;

@Service
public class DefaultTombolaGameplayService implements TombolaGameplayService {
    private final TombolaRepository tombolaRepository;
    private final AwardRepository awardRepository;

    DefaultTombolaGameplayService(TombolaRepository tombolaRepository, AwardRepository awardRepository) {
        this.tombolaRepository = tombolaRepository;
        this.awardRepository = awardRepository;
    }

    @Override
    public Tombola assignAward(Tombola tombola, Award award) {
        award.setTombola(tombola);
        awardRepository.save(award);

        tombola.addAward(award);
        return tombolaRepository.save(tombola);
    }

    @Override
    public boolean isAwardCapacityReached(Tombola tombola) {
        return tombola.getMaxAwards() == tombola.getAwards().size();
    }

    @Override
    public boolean isPlayerCapacityReached(Tombola tombola) {
        return tombola.getMaxPlayers() == tombola.getPlayers().size();
    }

    @Override
    public Tombola assignPlayer(Tombola tombola, Player player) {
        tombola.addPlayer(player);

        return tombolaRepository.save(tombola);
    }

    @Override
    public Tombola changeGameState(Tombola tombola, GameState gameState) {
        tombola.setGameState(gameState);

        return tombolaRepository.save(tombola);
    }
}
