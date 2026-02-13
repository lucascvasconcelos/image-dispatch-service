package com.br.image.image_dispatch_service.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "document_dispatch")
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDispatchEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String fileName;
    private String status;

    private String s3Url;

    private Instant createdAt;

    @Version
    private Long version;

    public DocumentDispatchEntity(UUID id, String fileName, String s3Url, Instant createdAt) {
        this.id = id;
        this.fileName = fileName;
        this.s3Url = s3Url;
        this.createdAt = createdAt;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }
}
