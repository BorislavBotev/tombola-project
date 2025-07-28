package com.bb.game.handler;

import com.bb.game.award.exception.AwardNotFoundException;
import com.bb.game.player.exception.PlayerNotFoundException;
import com.bb.game.tombola.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String MESSAGE = "message";
    private static final String STATUS = "status";
    private static final String DUPLICATE_ENTRY_ALREADY_EXIST = "Duplicate entry - already exist";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(STATUS, HttpStatus.BAD_REQUEST.value());

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (msg1, msg2) -> msg1
                ));

        errorResponse.put(MESSAGE, fieldErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({AwardNotFoundException.class, PlayerNotFoundException.class, TombolaNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(Exception ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({AwardCapacityException.class, PlayerCapacityException.class, PrerequisitesException.class})
    public ResponseEntity<Map<String, Object>> handleCapacityException(Exception ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return buildResponse(HttpStatus.CONFLICT, DUPLICATE_ENTRY_ALREADY_EXIST);
    }

    @ExceptionHandler({GameStateException.class, AwardAvailabilityException.class})
    public ResponseEntity<Map<String, Object>> handleGameStateException(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, status.value());
        response.put(MESSAGE, message);

        return new ResponseEntity<>(response, status);
    }
}
