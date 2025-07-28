package com.bb.game.award.controller;

import com.bb.game.award.dto.request.CreateAwardRequestDTO;
import com.bb.game.award.dto.response.AwardResponseDTO;
import com.bb.game.award.exception.AwardNotFoundException;
import com.bb.game.award.facade.AwardFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller containing endpoint for award logic
 */
@RestController
@Validated
@RequestMapping("/awards")
@Tag(name = "Award APIs")
public class AwardController {
    private final AwardFacade awardFacade;

    AwardController(AwardFacade awardFacade) {
        this.awardFacade = awardFacade;
    }

    /**
     * Post request for creating a new reward
     *
     * @param createAwardRequestDTO DTO representing the request body from the API call
     * @return ResponseEntity<AwardResponseDTO>
     */
    @PostMapping()
    public ResponseEntity<AwardResponseDTO> createAward(@Valid @NotNull
                                                        @RequestBody
                                                        CreateAwardRequestDTO createAwardRequestDTO) {
        AwardResponseDTO awardResponseDTO = awardFacade.createAward(createAwardRequestDTO);
        return new ResponseEntity<>(awardResponseDTO, HttpStatus.CREATED);
    }

    /**
     * Get request for retrieving all awards
     *
     * @return ResponseEntity<List<AwardResponseDTO>>
     */
    @GetMapping()
    public ResponseEntity<List<AwardResponseDTO>> getAllPlayers() {
        List<AwardResponseDTO> awards = awardFacade.getAllAwards();
        return new ResponseEntity<>(awards, HttpStatus.OK);
    }

    /**
     * Get request for retrieving an award by UUID
     *
     * @param uuid
     * @return ResponseEntity<AwardResponseDTO>
     * @throws AwardNotFoundException thrown when Award with that uuid does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<AwardResponseDTO> getAward(@PathVariable("id") String uuid) throws AwardNotFoundException {
        AwardResponseDTO awardResponseDTO = awardFacade.getAwardByUUID(uuid);
        return new ResponseEntity<>(awardResponseDTO, HttpStatus.OK);
    }
}