package org.jakos176;

import lombok.Builder;
import lombok.Value;
import org.jakos176.enums.ControlEnum;

import java.util.List;

@Builder
@Value
public class Control {

    List<ControlEnum> controlEnums;
}