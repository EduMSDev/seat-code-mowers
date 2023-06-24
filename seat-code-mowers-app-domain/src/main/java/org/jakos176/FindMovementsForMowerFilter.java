package org.jakos176;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FindMovementsForMowerFilter {

    String plateau;

    List<List<String>> fullMovement;
}
