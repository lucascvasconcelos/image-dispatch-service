package com.br.image.image_dispatch_service.presentation.controller;

import com.br.image.image_dispatch_service.domain.imagedispatch.exceptions.DocumentDispatchGeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentDispatchGeneralException.class)
    public ResponseEntity<String> handle(DocumentDispatchGeneralException ex) {
        return ResponseEntity
                .status(500)
                .body(ex.getMessage());
    }
}
