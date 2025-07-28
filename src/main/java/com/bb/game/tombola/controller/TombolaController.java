package com.bb.game.tombola.controller;

import com.bb.game.tombola.dto.request.CreateTombolaRequestDTO;
import com.bb.game.tombola.dto.response.TombolaResponseDTO;
import com.bb.game.tombola.exception.TombolaNotFoundException;
import com.bb.game.tombola.facade.TombolaFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller mainly for CRUD tombola endpoints
 */
@RestController
@Validated
@RequestMapping("/tombolas")
@Tag(name = "Tombola APIs")
public class TombolaController {
    private final TombolaFacade tombolaFacade;

    TombolaController(TombolaFacade tombolaFacade) {
        this.tombolaFacade = tombolaFacade;
    }

    /**
     * Post request for creating a tombola
     *
     * @param createTombolaRequestDTO request representing the request body
     * @return ResponseEntity<TombolaResponseDTO>
     */
    @PostMapping()
    public ResponseEntity<TombolaResponseDTO> createTombola(@Valid @NotNull
                                                            @RequestBody
                                                            CreateTombolaRequestDTO createTombolaRequestDTO) {
        TombolaResponseDTO responseDTO = tombolaFacade.createTombola(createTombolaRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Get request for retrieving all tombolas
     *
     * @return ResponseEntity<List < TombolaResponseDTO>>
     */
    @GetMapping()
    public ResponseEntity<List<TombolaResponseDTO>> getAllTombolas() {
        List<TombolaResponseDTO> tombolas = tombolaFacade.getAllTombolas();
        return new ResponseEntity<>(tombolas, HttpStatus.OK);
    }

    /**
     * Get specifics tombola by UUID
     *
     * @param uuid
     * @return ResponseEntity<TombolaResponseDTO>
     * @throws TombolaNotFoundException thrown when Tombola with the UUID does not eixst
     */
    @GetMapping("/{id}")
    public ResponseEntity<TombolaResponseDTO> getTombola(@PathVariable("id") String uuid) throws TombolaNotFoundException {
        TombolaResponseDTO tombola = tombolaFacade.getTombolaByUUID(uuid);
        return new ResponseEntity<>(tombola, HttpStatus.OK);
    }
}