package org.jakos176.exceptions;

public class InvalidCompassNumericSizePointException extends ApplicationException {

    public InvalidCompassNumericSizePointException(Integer invalidInteger) {
        super(String.format("The numerical values for the compass point must be between 0 and 9:" +
                        "Expected value: [0-9][0-9] Example: 12, Actual Value %s", invalidInteger),
                ApplicationExceptionCode.INVALID_NUMERIC_SIZE_POINT_ERROR);
    }
}