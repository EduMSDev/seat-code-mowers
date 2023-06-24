package org.jakos176.exceptions;

public class InvalidCompassPointEnumException extends ApplicationException {

    public InvalidCompassPointEnumException(String invalidString) {
        super(String.format("invalid value entered for CompassPoint. Supported values are NESW. Actual Value %s",
                invalidString), ApplicationExceptionCode.INVALID_COMPASS_POINT_ENUM_ERROR);
    }
}