package org.jakos176.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jakos176.exceptions.InvalidCompassPointEnumException;

import java.util.Arrays;

@RequiredArgsConstructor
public enum CompassPointEnum {

    NORTH("N"),

    SOUTH("S"),

    EAST("E"),

    WEST("W");

    @Getter
    private final String coordinate;

    public static CompassPointEnum asCompassPoint(String character) {
        return Arrays.stream(CompassPointEnum.values()).
                filter(compassPointEnum -> compassPointEnum.getCoordinate().equalsIgnoreCase(character))
                .findFirst()
                .orElseThrow(() -> new InvalidCompassPointEnumException(character));
    }

}
