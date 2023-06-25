package org.jakos176;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Value
public class Cell extends Position {

    @NonFinal
    @Setter
    @Builder.Default
    boolean isBusy = false;

    @NonFinal
    @Setter
    @Builder.Default
    boolean isCut = false;
}