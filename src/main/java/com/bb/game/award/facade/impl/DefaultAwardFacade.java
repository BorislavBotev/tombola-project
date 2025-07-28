package com.bb.game.award.facade.impl;

import com.bb.game.award.converter.AwardToAwardResponseDTOConverter;
import com.bb.game.award.converter.CreateAwardRequestDTOToAwardConverter;
import com.bb.game.award.dto.request.CreateAwardRequestDTO;
import com.bb.game.award.dto.response.AwardResponseDTO;
import com.bb.game.award.exception.AwardNotFoundException;
import com.bb.game.award.facade.AwardFacade;
import com.bb.game.award.model.Award;
import com.bb.game.award.service.AwardService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultAwardFacade implements AwardFacade {
    private final AwardService awardService;
    private final AwardToAwardResponseDTOConverter awardToAwardResponseDTOConverter;
    private final CreateAwardRequestDTOToAwardConverter createAwardRequestDTOToAwardConverter;

    DefaultAwardFacade(AwardService awardService, AwardToAwardResponseDTOConverter awardToAwardResponseDTOConverter,
                       CreateAwardRequestDTOToAwardConverter createAwardRequestDTOToAwardConverter) {
        this.awardService = awardService;
        this.awardToAwardResponseDTOConverter = awardToAwardResponseDTOConverter;
        this.createAwardRequestDTOToAwardConverter = createAwardRequestDTOToAwardConverter;
    }

    @Override
    public AwardResponseDTO createAward(CreateAwardRequestDTO createAwardRequestDTO) {
        Award award = createAwardRequestDTOToAwardConverter.convert(createAwardRequestDTO);
        Award createdAward = awardService.createAward(award);
        return awardToAwardResponseDTOConverter.convert(createdAward);
    }

    @Override
    public AwardResponseDTO getAwardByUUID(String uuid) throws AwardNotFoundException {
        Award award = awardService.getAwardByUUID(uuid)
                .orElseThrow(() -> new AwardNotFoundException(uuid));
        return awardToAwardResponseDTOConverter.convert(award);
    }

    @Override
    public List<AwardResponseDTO> getAllAwards() {
        List<Award> awards = awardService.getAllAwards();
        return awards.stream().map(awardToAwardResponseDTOConverter::convert).toList();
    }
}
