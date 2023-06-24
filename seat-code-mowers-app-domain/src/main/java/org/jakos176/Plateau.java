package org.jakos176;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Plateau {

    Integer upperRight;

    Integer upperLeft;

    @Builder.Default
    Integer bottomRight = 0;

    @Builder.Default
    Integer bottomLeft = 0;
}
