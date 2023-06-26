package org.jakos176.queries;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FindMovementsForMowersQuery implements Query {

    String plateau;

    List<String> fullMovement;
}
