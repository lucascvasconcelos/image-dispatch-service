package com.br.image.image_dispatch_service.application.usecase.imagedispatch.impl;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import com.br.image.image_dispatch_service.domain.imagedispatch.exceptions.DocumentDispatchGeneralException;
import com.br.image.image_dispatch_service.infrastructure.aws.S3Service;
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
    private final S3Service s3Service;

    @Override
    @Transactional
    public void dispatch(MultipartFile file) {

        log.info("Iniciando processamento do documento. fileName={}", file.getOriginalFilename());

        try {
            String s3Url = s3Service.uploadFile(file);
            log.info("Arquivo enviado para S3. url={}", s3Url);

            DocumentDispatchEntity document = new DocumentDispatchEntity();
            document.setFileName(file.getOriginalFilename());
            document.setStatus("DISPATCHED");
            document.setCreatedAt(Instant.now());
            document.setS3Url(s3Url);

            repository.save(document);

            log.info("Documento salvo com sucesso. fileName={}", file.getOriginalFilename());

        } catch (Exception e) {
            log.error(
                    "Erro ao processar dispatch do documento. fileName={}",
                    file.getOriginalFilename(),
                    e
            );

            throw new DocumentDispatchGeneralException(
                    "Falha ao processar envio do documento",
                    e
            );
        }
    }

}
