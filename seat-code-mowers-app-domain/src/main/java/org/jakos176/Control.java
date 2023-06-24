package org.jakos176;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Control {

    LEFT("L"),
    RIGHT("R"),
    MOVEMENT("M");

    private final String direction;
}
