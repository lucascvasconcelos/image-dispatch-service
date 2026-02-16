package com.br.image.image_dispatch_service.domain.imagedispatch.model;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class DocumentDispatch {

    private UUID id;

    private String fileName;

    private String s3Url;

    private Instant createdAt;

    public DocumentDispatch(UUID id, String fileName, String s3Url, Instant createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.s3Url = s3Url;
        this.createdAt = createdAt;
    }
}
