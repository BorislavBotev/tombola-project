package com.bb.game.award.service.impl;

import com.bb.game.award.model.Award;
import com.bb.game.award.repository.AwardRepository;
import com.bb.game.award.service.AwardService;
import com.bb.game.player.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAwardService implements AwardService {
    private final AwardRepository awardRepository;

    DefaultAwardService(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @Override
    public Award createAward(Award award) {
        return awardRepository.save(award);
    }

    @Override
    public Optional<Award> getAwardByUUID(String uuid) {
        return awardRepository.findByUuid(uuid);
    }

    @Override
    public List<Award> getAllAwards() {
        return awardRepository.findAll();
    }

    @Override
    public Award setPlayer(Award award, Player player) {
        award.setPlayer(player);
        return awardRepository.save(award);
    }
}
