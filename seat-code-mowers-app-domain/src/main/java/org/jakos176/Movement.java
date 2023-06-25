package org.jakos176;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Movement {

    CompassPoint compassPoint;

    Control control;
}
