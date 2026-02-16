package com.br.image.image_dispatch_service.application.usecase.imagedispatch.impl;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import com.br.image.image_dispatch_service.domain.imagedispatch.exceptions.DocumentDispatchGeneralException;
import com.br.image.image_dispatch_service.domain.imagedispatch.model.DocumentDispatch;
import com.br.image.image_dispatch_service.infrastructure.aws.S3Service;
import com.br.image.image_dispatch_service.infrastructure.mail.SmtpEmailSender;
import com.br.image.image_dispatch_service.infrastructure.persistence.jpa.DocumentDispatchEntity;
import com.br.image.image_dispatch_service.infrastructure.persistence.jpa.DocumentDispatchJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class DocumentDispatchUseCaseImpl implements DocumentDispatchUseCase {

    private final DocumentDispatchJpaRepository repository;
    private final S3Service s3Service;
    private final SmtpEmailSender smtpEmailSender;

    @Transactional
    @Override
    public void dispatch(MultipartFile file) {
        try {

            log.info("📥 Início do dispatch | fileName={}, size={} bytes, contentType={}",
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType()
            );

            // todo 1️⃣ Upload - a concluir
            //String url = s3Service.uploadFile(file);

            log.info("✅ Upload concluído | s3Url={}", "url");

            List<String> recipients = List.of(
                    "marcelo.cologneze@segurosunimed.com.br",
                    "leonardo.nascimento.rgr@segurosunimed.com.br",
                    "andre.souza@segurosunimed.com.br"
            );

            log.info("📧 Iniciando envio de emails | totalDestinatarios={}", recipients.size());

            for (String email : recipients) {
                smtpEmailSender.send(
                        email,
                        "Documento enviado",
                        "Seu documento foi enviado com sucesso.\nURL: " + "url"
                );

                log.info("📨 Email enviado com sucesso | to={}", email);
            }
            DocumentDispatch document =
                    new DocumentDispatch(UUID.randomUUID(), file.getOriginalFilename(), "url", Instant.now());

            DocumentDispatchEntity documentEntity = new DocumentDispatchEntity(
                    UUID.randomUUID(), file.getOriginalFilename(), "url", document.getCreatedAt()
            );
            repository.save(documentEntity);

            log.info("💾 Documento persistido com sucesso | entityId={}", documentEntity.getId());
            log.info("🎉 Dispatch finalizado com sucesso | fileName={}", file.getOriginalFilename());
        } catch (Exception ex) {
            throw new DocumentDispatchGeneralException(
                    "Erro ao realizar dispatch do documento", ex
            );
        }
    }

}
