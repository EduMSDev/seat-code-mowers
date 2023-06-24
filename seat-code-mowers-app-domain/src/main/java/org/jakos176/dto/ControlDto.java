package org.jakos176.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ControlDto {

    LEFT("L"),
    RIGHT("R"),
    MOVEMENT("M");

    private final String direction;
}
