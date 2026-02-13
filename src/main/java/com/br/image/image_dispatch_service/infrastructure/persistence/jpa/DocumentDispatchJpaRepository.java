package com.br.image.image_dispatch_service.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentDispatchJpaRepository
        extends JpaRepository<DocumentDispatchEntity, UUID> {


}