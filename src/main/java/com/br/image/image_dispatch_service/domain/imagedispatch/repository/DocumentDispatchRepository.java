package com.br.image.image_dispatch_service.domain.imagedispatch.repository;

import com.br.image.image_dispatch_service.domain.imagedispatch.model.DocumentDispatch;

public interface DocumentDispatchRepository {
    void save(DocumentDispatch documentDispatch);
}