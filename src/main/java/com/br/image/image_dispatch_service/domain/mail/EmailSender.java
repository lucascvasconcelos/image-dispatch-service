package com.br.image.image_dispatch_service.domain.mail;

import org.springframework.web.multipart.MultipartFile;

public interface EmailSender {
     void send(String to, String subject, String body, MultipartFile attachment);
}
