package org.jakos176.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationExceptionCode {

    INVALID_PLATEAU_ERROR(1),
    INVALID_NUMERIC_PLATEAU_ERROR(2);


    private final Integer code;
}
