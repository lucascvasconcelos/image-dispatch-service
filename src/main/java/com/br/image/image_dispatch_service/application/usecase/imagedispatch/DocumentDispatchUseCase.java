package com.br.image.image_dispatch_service.application.usecase.imagedispatch;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentDispatchUseCase {
    void dispatch(MultipartFile file);

}
