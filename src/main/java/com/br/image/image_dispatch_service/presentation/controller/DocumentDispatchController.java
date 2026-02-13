package com.br.image.image_dispatch_service.presentation.controller;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import com.br.image.image_dispatch_service.domain.imagedispatch.repository.DocumentDispatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentDispatchController {

    private final DocumentDispatchUseCase useCase;

    @PostMapping("/dispatch")
    public ResponseEntity<Void> dispatchDocument(
            @RequestParam("file") MultipartFile file
    ) {

        useCase.dispatch(file);

        log.info("Recebida requisição para dispatch de documento. fileName={}, size={} bytes, contentType={}",
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType()
        );

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("suyane!!!!!!!!");
    }
}