package com.br.image.image_dispatch_service.application.usecase.imagedispatch.impl;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import com.br.image.image_dispatch_service.infrastructure.persistence.jpa.DocumentDispatchEntity;
import com.br.image.image_dispatch_service.infrastructure.persistence.jpa.DocumentDispatchJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Log4j2
@Service
@RequiredArgsConstructor
public class DocumentDispatchUseCaseImpl implements DocumentDispatchUseCase {

    private final DocumentDispatchJpaRepository repository;

    @Override
    @Transactional
    public void dispatch(MultipartFile file) {
        log.info("Iniciando processamento do documento. fileName={}", file.getOriginalFilename());

        DocumentDispatchEntity document = new DocumentDispatchEntity();
        document.setFileName(file.getOriginalFilename());
        document.setStatus("DISPATCHDED");
        document.setCreatedAt(Instant.now());

        repository.save(document);
    }
}
