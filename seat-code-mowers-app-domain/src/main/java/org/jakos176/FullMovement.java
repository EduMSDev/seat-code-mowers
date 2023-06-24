package org.jakos176;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FullMovement {

    Plateau plateau;

    Movement[] movement;
}
