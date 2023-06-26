package org.jakos176;

import lombok.Builder;
import lombok.Value;
import org.jakos176.enums.ControlEnum;

import java.util.List;

@Builder
@Value
public class Control {

    List<ControlEnum> controlEnums;

    @Override
    public String toString() {
        String s = "Control{" +
                "controlEnums=";
        List<String> list = controlEnums.stream().map(ControlEnum::getDirection).toList();
        return s + list +
                '}';
    }
}