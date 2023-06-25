package org.jakos176.exceptions;

public class InvalidNumericControlException extends ApplicationException {

    public InvalidNumericControlException(String invalidNumeric) {
        super(String.format("Numeric values are not supported. Only letters are accepted. Expected value: 'LMR', " +
                        "Actual Value %s", invalidNumeric),
                ApplicationExceptionCode.INVALID_NUMERIC_CONTROL_ERROR);
    }
}
