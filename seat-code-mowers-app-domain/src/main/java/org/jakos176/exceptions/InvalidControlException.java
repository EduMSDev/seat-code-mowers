package org.jakos176.exceptions;

public class InvalidControlException extends ApplicationException {

    public InvalidControlException(String invalidString) {
        super(String.format("Only the letters LMR are supported. Expected value: 'LMR', Actual Value %s", invalidString),
                ApplicationExceptionCode.INVALID_CONTROL_ERROR);
    }
}
