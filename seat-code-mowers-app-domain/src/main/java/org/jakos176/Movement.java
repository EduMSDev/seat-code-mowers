package org.jakos176;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Builder
@Value
public class Movement {

    @NonFinal
    @Setter
    CompassPoint compassPoint;

    Control control;
}
