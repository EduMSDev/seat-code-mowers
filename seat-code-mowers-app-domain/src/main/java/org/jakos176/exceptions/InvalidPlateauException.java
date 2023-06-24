package org.jakos176.exceptions;

public class InvalidPlateauException extends ApplicationException {

    public InvalidPlateauException(String invalidPlateau) {
        super(String.format("Invalid input for Plateau: Expected input: '5 5', Actual Value %s", invalidPlateau),
                ApplicationExceptionCode.INVALID_PLATEAU_ERROR);
    }
}
