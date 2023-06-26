package org.jakos176;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.jakos176.enums.CompassPointEnum;

@Builder
@Value
public class CompassPoint {

    Integer pointX;

    Integer pointY;

    @NonFinal
    @Setter
    CompassPointEnum compassPointEnum;

    @Override
    public String toString() {
        return "CompassPoint{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", compassPointEnum=" + compassPointEnum +
                '}';
    }
}