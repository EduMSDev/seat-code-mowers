package org.jakos176.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CompassPointDto {

    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private final String coordinate;
}
