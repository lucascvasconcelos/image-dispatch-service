package com.br.image.image_dispatch_service.domain.imagedispatch.exceptions;

public class DocumentDispatchGeneralException extends RuntimeException {
    public DocumentDispatchGeneralException(String message) {
        super(message);
    }

    public DocumentDispatchGeneralException(String message, Throwable cause) {
        super(message, cause);
    }
}
