package com.br.image.image_dispatch_service.infrastructure.mail;

import com.br.image.image_dispatch_service.domain.imagedispatch.exceptions.DocumentDispatchGeneralException;
import com.br.image.image_dispatch_service.domain.mail.EmailSender;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String subject, String body, MultipartFile attachment) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, false);

            helper.addAttachment(
                    Objects.requireNonNull(attachment.getOriginalFilename()),
                    new ByteArrayResource(attachment.getBytes()),
                    Objects.requireNonNull(attachment.getContentType())
            );

            mailSender.send(message);
            log.info("Email enviado com sucesso. to={}", to);
        } catch (Exception ex) {
            log.error("Erro ao enviar email", ex);
            throw new DocumentDispatchGeneralException("Falha ao enviar email");
        }
    }
}
