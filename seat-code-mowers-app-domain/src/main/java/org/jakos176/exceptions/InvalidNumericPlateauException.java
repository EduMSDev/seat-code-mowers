package org.jakos176.exceptions;

public class InvalidNumericPlateauException extends ApplicationException {

    public InvalidNumericPlateauException(Character invalidCharacter) {
        super(String.format("Invalid input for Plateau: Expected Integer: '5', Actual Value %s", invalidCharacter),
                ApplicationExceptionCode.INVALID_NUMERIC_PLATEAU_ERROR);
    }
}
