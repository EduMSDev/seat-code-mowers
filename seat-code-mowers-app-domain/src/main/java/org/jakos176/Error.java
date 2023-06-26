package org.jakos176;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Error {

    Integer code;

    String message;
}