package com.br.image.image_dispatch_service.infrastructure.persistence.jpa;

import com.br.image.image_dispatch_service.domain.imagedispatch.model.DocumentDispatch;
import com.br.image.image_dispatch_service.domain.imagedispatch.repository.DocumentDispatchRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDispatchRepositoryImpl implements DocumentDispatchRepository {

    private final DocumentDispatchJpaRepository jpaRepository;

    public DocumentDispatchRepositoryImpl(DocumentDispatchJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(DocumentDispatch document) {
        DocumentDispatchEntity entity = new DocumentDispatchEntity();
        entity.setId(document.getId());
        entity.setFileName(document.getFileName());
        entity.setCreatedAt(document.getCreatedAt());

        jpaRepository.save(entity);
    }

}