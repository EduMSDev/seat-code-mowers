package org.jakos176;

import lombok.Builder;
import lombok.Value;
import org.jakos176.enums.CompassPointEnum;

@Builder
@Value
public class CompassPoint {

    Integer pointX;

    Integer pointY;

    CompassPointEnum compassPointEnum;
}