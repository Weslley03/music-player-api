package com.weftecnologia.music_player_api.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.weftecnologia.music_player_api.exception.handler.exceptions.GenericNotFoundException;

@RestControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidatonsErros(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(err -> {
      errors.put(err.getField(), err.getDefaultMessage());
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        Map.of(
            "message", "erro de validação.",
            "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
            "errors", errors));
  }

  @ExceptionHandler(GenericNotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(GenericNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        Map.of(
            "message", ex.getMessage(),
            "status", String.valueOf(HttpStatus.NOT_FOUND.value())));
  }
}