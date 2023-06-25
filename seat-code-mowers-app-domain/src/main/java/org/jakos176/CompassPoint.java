package org.jakos176;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.jakos176.enums.CompassPointEnum;

@Builder
@Value
@Getter
public class CompassPoint {

    Integer pointX;

    Integer pointY;

    CompassPointEnum compassPointEnum;
}