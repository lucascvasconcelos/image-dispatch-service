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
                    "suyanefarmacia@gmail.com",
                    "lks.lucasvasconcelos@gmail.com",
                    "mclaracontact@gmail.com"
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

            DocumentDispatch document = new DocumentDispatch(
                    file.getOriginalFilename(), "url", Instant.now()
            );

            DocumentDispatchEntity documentEntity = new DocumentDispatchEntity(
                    file.getOriginalFilename(), "url", document.getCreatedAt()
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

