package org.jakos176;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CompassPoint {

    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private final String coordinate;
}
