package org.jakos176.exceptions;

public class OutOfRangePositionException extends ApplicationException {

    public OutOfRangePositionException(Integer pointX, Integer pointY) {
        super(String.format("The mower can't move to the position. Out of range from Plateau Position %s %s", pointX, pointY),
                ApplicationExceptionCode.OUT_OF_RANGE_POSITION_ERROR);
    }
}