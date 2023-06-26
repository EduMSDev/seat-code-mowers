package org.jakos176.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlateauDto {

    Integer upperRight;

    Integer upperLeft;

    @Builder.Default
    Integer bottomRight = 0;

    @Builder.Default
    Integer bottomLeft = 0;
}
