package com.br.image.image_dispatch_service.infrastructure.persistence.config;

import com.br.image.image_dispatch_service.domain.mail.EmailSender;
import com.br.image.image_dispatch_service.infrastructure.mail.SmtpEmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    @Bean
    public EmailSender emailSender(JavaMailSender mailSender) {
        return new SmtpEmailSender(mailSender);
    }
}
