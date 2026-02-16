package com.br.image.image_dispatch_service.domain.mail;

public interface EmailSender {
    void send(
            String to,
            String subject,
            String body
    );
}
