package org.jakos176.exceptions;

public class InvalidPlateauException extends ApplicationException {

    public InvalidPlateauException(Character invalidCharacter) {
        super(String.format("Invalid input for Plateau: Expected Integer: '5', Actual Value %s", invalidCharacter),
                ApplicationExceptionCode.INVALID_PLATEAU_ERROR);
    }
}
