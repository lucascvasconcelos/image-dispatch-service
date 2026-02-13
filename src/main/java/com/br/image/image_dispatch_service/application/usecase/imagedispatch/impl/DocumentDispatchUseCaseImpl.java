package com.br.image.image_dispatch_service.application.usecase.imagedispatch.impl;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import com.br.image.image_dispatch_service.domain.imagedispatch.model.DocumentDispatch;
import com.br.image.image_dispatch_service.domain.imagedispatch.repository.DocumentDispatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class DocumentDispatchUseCaseImpl implements DocumentDispatchUseCase {

    private final DocumentDispatchRepository repository;

    @Override
    public void dispatch(MultipartFile file) {

        log.info("Iniciando processamento do documento. fileName={}", file.getOriginalFilename());

        DocumentDispatch document = new DocumentDispatch(
                UUID.randomUUID(),
                "fileName",
                "RECEIVED",
                Instant.now()
        );

        repository.save(document);
    }
}
