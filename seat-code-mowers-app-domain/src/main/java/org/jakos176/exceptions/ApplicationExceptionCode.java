package org.jakos176.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApplicationExceptionCode {

    INVALID_PLATEAU_ERROR(1),

    INVALID_SIZE_PLATEAU_ERROR(2),

    INVALID_COMPASS_POINT_ENUM_ERROR(3),

    INVALID_NUMERIC_SIZE_COMPASS_POINT_ERROR(4),

    INVALID_COMPASS_POINT_ERROR(5),

    INVALID_CONTROL_ERROR(6),

    INVALID_NUMERIC_CONTROL_ERROR(7);

    @Getter
    private final Integer code;
}
