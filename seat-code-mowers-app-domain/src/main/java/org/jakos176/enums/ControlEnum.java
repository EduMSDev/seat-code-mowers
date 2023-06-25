package org.jakos176.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jakos176.exceptions.InvalidControlException;

import java.util.Arrays;

@RequiredArgsConstructor
public enum ControlEnum {

    LEFT("L"),
    RIGHT("R"),
    MOVEMENT("M");

    @Getter
    private final String direction;

    public static ControlEnum asControlEnum(String control) {
        return Arrays.stream(ControlEnum.values())
                .filter(controlEnum -> controlEnum.getDirection().equalsIgnoreCase(control))
                .findFirst()
                .orElseThrow(() -> new InvalidControlException(control));
    }
}