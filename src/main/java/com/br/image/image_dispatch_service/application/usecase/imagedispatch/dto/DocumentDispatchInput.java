package com.br.image.image_dispatch_service.application.usecase.imagedispatch.dto;

import org.springframework.web.multipart.MultipartFile;

public record DocumentDispatchInput(
        MultipartFile file
) {}