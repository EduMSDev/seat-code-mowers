package org.jakos176.exceptions;

public class InvalidSizePlateauException extends ApplicationException {

    public InvalidSizePlateauException(String invalidPlateau) {
        super(String.format("Invalid input for Plateau: Two String values supported: '5 5',Actual Value %s", invalidPlateau),
                ApplicationExceptionCode.INVALID_SIZE_PLATEAU_ERROR);
    }
}
