package com.bb.game.player.service.impl;

import com.bb.game.award.model.Award;
import com.bb.game.player.model.Player;
import com.bb.game.player.repository.PlayerRepository;
import com.bb.game.player.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPlayerService implements PlayerService {
    private final PlayerRepository playerRepository;

    DefaultPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getPlayerByUUID(String uuid) {
        return playerRepository.findByUuid(uuid);
    }

    @Override
    public Player addAward(Player player, Award award) {
        List<Award> awards = player.getAwards();
        awards.add(award);
        player.setAwards(awards);
        return playerRepository.save(player);
    }
}
