package org.jakos176;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jakos176.enums.CompassPointEnum;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Value
public class CompassPoint extends Position {

    CompassPointEnum compassPointEnum;
}