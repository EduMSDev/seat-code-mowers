package org.jakos176.exceptions;

public class InvalidCompassPointException extends ApplicationException {

    public InvalidCompassPointException(String invalidString) {
        super(String.format("Numeric values must be only two and the value of the letter must be unique. " +
                        "Expected value: 12W, Actual Value %s",
                invalidString), ApplicationExceptionCode.INVALID_COMPASS_POINT_ERROR);
    }
}