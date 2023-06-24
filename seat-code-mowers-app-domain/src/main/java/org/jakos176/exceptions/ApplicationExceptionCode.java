package org.jakos176.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationExceptionCode {

    INVALID_PLATEAU_ERROR(1),

    INVALID_NUMERIC_PLATEAU_ERROR(2),

    INVALID_COMPASS_POINT_ENUM_ERROR(3),

    INVALID_NUMERIC_SIZE_POINT_ERROR(4),

    INVALID_COMPASS_POINT_ERROR(5);

    private final Integer code;
}
