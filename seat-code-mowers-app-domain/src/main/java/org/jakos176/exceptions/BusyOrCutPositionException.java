package org.jakos176.exceptions;

public class BusyOrCutPositionException extends ApplicationException {

    public BusyOrCutPositionException(Integer pointX, Integer pointY) {
        super(String.format("The mower can't move to the position. Is cutted or busy. Position %s %s", pointX, pointY),
                ApplicationExceptionCode.BUSY_OR_CUT_POSITION_ERROR);
    }
}