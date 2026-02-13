package com.br.image.image_dispatch_service.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentDispatchJpaRepository extends JpaRepository<DocumentDispatchEntity, UUID> {
}
