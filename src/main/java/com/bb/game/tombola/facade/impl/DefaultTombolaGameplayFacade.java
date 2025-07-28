package com.bb.game.tombola.facade.impl;

import com.bb.game.award.converter.AwardToMetadataDTOConverter;
import com.bb.game.award.exception.AwardNotFoundException;
import com.bb.game.award.model.Award;
import com.bb.game.award.service.AwardService;
import com.bb.game.player.converter.PlayerToMetadataDTOConverter;
import com.bb.game.player.exception.PlayerNotFoundException;
import com.bb.game.player.model.Player;
import com.bb.game.player.service.PlayerService;
import com.bb.game.tombola.converter.TombolaToTombolaResponseDTOConverter;
import com.bb.game.tombola.dto.request.IDRequestDTO;
import com.bb.game.tombola.dto.request.WinnersSelectionIDDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.dto.response.TombolaWinnersDTO;
import com.bb.game.tombola.dto.response.WinnersListDTO;
import com.bb.game.tombola.enums.GameState;
import com.bb.game.tombola.enums.WinnersSelection;
import com.bb.game.tombola.exception.*;
import com.bb.game.tombola.facade.TombolaGameplayFacade;
import com.bb.game.tombola.model.Tombola;
import com.bb.game.tombola.selection.impl.WinnersSelectionFactory;
import com.bb.game.tombola.service.TombolaGameplayService;
import com.bb.game.tombola.service.TombolaService;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class DefaultTombolaGameplayFacade implements TombolaGameplayFacade {
    private static final String AWARD_AVAILABILITY_MESSAGE = "Award is already assigned to a tombola - {0}";
    private static final String GAME_ANNOUNCEMENT = "Game has completed";
    private static final String GAME_STATE_MESSAGE = "Game was already played";

    private final TombolaGameplayService tombolaGameplayService;
    private final TombolaService tombolaService;
    private final AwardService awardService;
    private final PlayerService playerService;
    private final WinnersSelectionFactory winnersSelectionFactory;
    private final AwardToMetadataDTOConverter awardToMetadataDTOConverter;
    private final PlayerToMetadataDTOConverter playerToMetadataDTOConverter;
    private final TombolaToTombolaResponseDTOConverter tombolaToTombolaResponseDTOConverter;

    DefaultTombolaGameplayFacade(TombolaGameplayService tombolaGameplayService, TombolaService tombolaService, AwardService awardService, PlayerService playerService,
                                 WinnersSelectionFactory winnersSelectionFactory, PlayerToMetadataDTOConverter playerToMetadataDTOConverter,
                                 AwardToMetadataDTOConverter awardToMetadataDTOConverter, TombolaToTombolaResponseDTOConverter tombolaToTombolaResponseDTOConverter) {
        this.tombolaGameplayService = tombolaGameplayService;
        this.awardService = awardService;
        this.playerService = playerService;
        this.tombolaToTombolaResponseDTOConverter = tombolaToTombolaResponseDTOConverter;
        this.winnersSelectionFactory = winnersSelectionFactory;
        this.playerToMetadataDTOConverter = playerToMetadataDTOConverter;
        this.awardToMetadataDTOConverter = awardToMetadataDTOConverter;
        this.tombolaService = tombolaService;
    }

    @Override
    public TombolaResponseDTO assignAward(String tombolaUUID, IDRequestDTO idRequestDTO) throws AwardNotFoundException,
            TombolaNotFoundException, AwardCapacityException, AwardAvailabilityException {
        Tombola tombola = tombolaService.getTombolaByUUID(tombolaUUID)
                .orElseThrow(() -> new TombolaNotFoundException(tombolaUUID));
        checkAwardCapacity(tombola);

        Award award = awardService.getAwardByUUID(idRequestDTO.uuid())
                .orElseThrow(() -> new AwardNotFoundException(idRequestDTO.uuid()));
        checkAwardAvailability(award);

        Tombola updatedTombola = tombolaGameplayService.assignAward(tombola, award);
        return tombolaToTombolaResponseDTOConverter.convert(updatedTombola);
    }

    @Override
    public TombolaResponseDTO assignPlayer(String tombolaUUID, IDRequestDTO playerIDRequestDTO) throws TombolaNotFoundException, PlayerCapacityException, PlayerNotFoundException {
        Tombola tombola = tombolaService.getTombolaByUUID(tombolaUUID)
                .orElseThrow(() -> new TombolaNotFoundException(tombolaUUID));
        checkPlayerCapacity(tombola);

        Player player = playerService.getPlayerByUUID(playerIDRequestDTO.uuid())
                .orElseThrow(() -> new PlayerNotFoundException(playerIDRequestDTO.uuid()));

        Tombola updatedTombola = tombolaGameplayService.assignPlayer(tombola, player);
        return tombolaToTombolaResponseDTOConverter.convert(updatedTombola);
    }

    @Override
    public TombolaWinnersDTO play(String tombolaUUID, WinnersSelectionIDDTO winnersSelectionIDDTO) throws TombolaNotFoundException, PrerequisitesException, GameErrorException, GameStateException, WinnersSelectionException {
        Tombola tombola = tombolaService.getTombolaByUUID(tombolaUUID)
                .orElseThrow(() -> new TombolaNotFoundException(tombolaUUID));

        checkTombolaPrerequisite(tombola);
        tombola = tombolaGameplayService.changeGameState(tombola, GameState.ACTIVE);

        WinnersSelection winnerSelection = WinnersSelection.getWinnersSelectionBasedOnID(winnersSelectionIDDTO.id());
        Map<Award, Player> winners = winnersSelectionFactory
                .getStrategy(winnerSelection).selectWinners(tombola.getPlayers(), tombola.getAwards());
        return finalizeTombola(tombola, winners);
    }

    private void checkAwardCapacity(Tombola tombola) throws AwardCapacityException {
        boolean awardCapacity = tombolaGameplayService.isAwardCapacityReached(tombola);
        if (awardCapacity) {
            throw new AwardCapacityException(tombola.getMaxAwards());
        }
    }

    private void checkPlayerCapacity(Tombola tombola) throws PlayerCapacityException {
        boolean playerCapacity = tombolaGameplayService.isPlayerCapacityReached(tombola);
        if (playerCapacity) {
            throw new PlayerCapacityException(tombola.getMaxPlayers());
        }
    }

    private void checkTombolaPrerequisite(Tombola tombola) throws PrerequisitesException, GameStateException {
        boolean prerequisites = tombolaGameplayService.isPlayerCapacityReached(tombola)
                && tombolaGameplayService.isAwardCapacityReached(tombola);
        if (!prerequisites) {
            throw new PrerequisitesException();
        }
        if (tombola.getGameState() != GameState.WAITING) {
            throw new GameStateException(GAME_STATE_MESSAGE);
        }
    }

    private TombolaWinnersDTO finalizeTombola(Tombola tombola, Map<Award, Player> winners) throws GameErrorException {
        if (winners.size() != tombola.getAwards().size()) {
            throw new GameErrorException(winners.size(), tombola.getAwards().size());
        }
        updateWinnersEntities(winners);
        tombolaGameplayService.changeGameState(tombola, GameState.COMPLETED);
        return generateResponseDTO(winners);
    }

    private TombolaWinnersDTO generateResponseDTO(Map<Award, Player> winners) {
        List<WinnersListDTO> winnersList = new ArrayList<>();
        winners.forEach((award, player) -> {
            winnersList.add(new WinnersListDTO(awardToMetadataDTOConverter.convert(award), playerToMetadataDTOConverter.convert(player)));
        });
        return new TombolaWinnersDTO(GAME_ANNOUNCEMENT, winnersList);
    }

    private void updateWinnersEntities(Map<Award, Player> winners) {
        winners.forEach((award, player) -> {
            playerService.addAward(player, award);
            awardService.setPlayer(award, player);
        });
    }

    private void checkAwardAvailability(Award award) throws AwardAvailabilityException {
        if (Objects.nonNull(award.getTombola())) {
            throw new AwardAvailabilityException(MessageFormat.format(AWARD_AVAILABILITY_MESSAGE, award.getTombola().getUuid()));
        }
    }

}
