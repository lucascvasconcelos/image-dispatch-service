package com.br.image.image_dispatch_service.application.usecase.imagedispatch.dto;

import java.time.Instant;
import java.util.UUID;

public record DocumentDispatchOutput(
        UUID id,
        String fileName,
        String s3Url,
        Instant createdAt
) {}