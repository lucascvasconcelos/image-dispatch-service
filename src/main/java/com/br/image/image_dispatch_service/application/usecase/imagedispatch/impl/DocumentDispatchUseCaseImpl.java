package com.br.image.image_dispatch_service.application.usecase.imagedispatch.impl;

import com.br.image.image_dispatch_service.application.usecase.imagedispatch.DocumentDispatchUseCase;
import org.springframework.web.multipart.MultipartFile;

public class DocumentDispatchUseCaseImpl implements DocumentDispatchUseCase {
    @Override
    public void dispatch(MultipartFile file) {
        // implementação futura:
        // 1. upload S3
        // 2. salvar no banco
        // 3. enviar emails
    }
}
