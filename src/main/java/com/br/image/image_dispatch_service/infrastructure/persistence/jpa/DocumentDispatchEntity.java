package com.br.image.image_dispatch_service.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "document_dispatch")
public class DocumentDispatchEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String fileName;

    private String s3Url;

    private Instant createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }
}