package org.jakos176.exceptions;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

    @Getter
    private final ApplicationExceptionCode applicationExceptionCode;

    public ApplicationException(String message, ApplicationExceptionCode applicationExceptionCode) {
        super(message);
        this.applicationExceptionCode = applicationExceptionCode;
    }

    public ApplicationException(Throwable cause, ApplicationExceptionCode applicationExceptionCode) {
        super(cause);
        this.applicationExceptionCode = applicationExceptionCode;
    }
}
