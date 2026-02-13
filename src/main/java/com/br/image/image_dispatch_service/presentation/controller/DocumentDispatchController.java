package com.br.image.image_dispatch_service.presentation.controller;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentDispatchController {

    @PostMapping("/dispatch")
    public ResponseEntity<Void> dispatchDocument(
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("suyane!!!!!!!!");
    }
}