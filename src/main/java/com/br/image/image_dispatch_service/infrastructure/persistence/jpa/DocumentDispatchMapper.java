package com.br.image.image_dispatch_service.infrastructure.persistence.jpa;

import com.br.image.image_dispatch_service.domain.imagedispatch.model.DocumentDispatch;

public class DocumentDispatchMapper {

    private DocumentDispatchMapper() {
    }

    public static DocumentDispatchEntity toEntity(DocumentDispatch domain) {
        if (domain == null) {
            return null;
        }

        DocumentDispatchEntity entity = new DocumentDispatchEntity();
        entity.setId(domain.getId());
        entity.setFileName(domain.getFileName());
        entity.setS3Url(domain.getS3Url());
        entity.setCreatedAt(domain.getCreatedAt());

        return entity;
    }

    public static DocumentDispatch toDomain(DocumentDispatchEntity entity) {
        if (entity == null) {
            return null;
        }

        return new DocumentDispatch(
                entity.getFileName(),
                entity.getS3Url(),
                entity.getCreatedAt()
        );
    }
}
