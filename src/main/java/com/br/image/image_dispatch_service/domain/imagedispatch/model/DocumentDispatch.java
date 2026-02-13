package com.br.image.image_dispatch_service.domain.imagedispatch.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DocumentDispatch {

    private UUID id;

    private String fileName;

    private String s3Url;

    private Instant createdAt;

}
