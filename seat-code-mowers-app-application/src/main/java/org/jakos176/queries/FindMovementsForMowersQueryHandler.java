package org.jakos176.queries;

import org.jakos176.FullMovement;
import reactor.core.publisher.Mono;

public class FindMovementsForMowersQueryHandler implements QueryHandler<FindMovementsForMowersQuery, Mono<FullMovement>> {

    //todo implements valids annotations
    public Mono<FullMovement> execute(FindMovementsForMowersQuery query) {
        return null;
    }
}
