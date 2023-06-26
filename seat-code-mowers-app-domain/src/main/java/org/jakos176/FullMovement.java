package org.jakos176;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class FullMovement {

    Plateau plateau;

    List<Movement> movement;
}
