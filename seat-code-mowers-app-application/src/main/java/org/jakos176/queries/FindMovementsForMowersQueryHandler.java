package org.jakos176.queries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.FullMovement;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
//todo include validated annotation
@Slf4j
public class FindMovementsForMowersQueryHandler implements
        QueryHandler<FindMovementsForMowersQuery, Mono<FullMovement>> {

    FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper;


    //todo implements valids annotations
    public Mono<FullMovement> execute(FindMovementsForMowersQuery query) {
        FindMovementsForMowerFilter findMovementsForMowerFilter = findMovementsForMowersQueryMapper
                .asFindMovementsForMowerFilter(query);
        
//        return Mono.just(query).doOnNext(querylogs -> log.warn(""));


        return null;
    }
}
