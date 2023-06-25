package org.jakos176.queries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jakos176.CompassPoint;
import org.jakos176.FullMovement;
import org.jakos176.Movement;
import org.jakos176.Plateau;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
//todo include validated annotation and logs
@Slf4j
public class FindMovementsForMowersQueryHandler implements
        QueryHandler<FindMovementsForMowersQuery, Mono<FullMovement>> {

    FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper;


    //todo implements valids annotations
    public Mono<FullMovement> execute(FindMovementsForMowersQuery query) {

        return Mono.just(query).doOnNext(querylogs -> log.debug(""))
                .map(findMovementsForMowersQuery -> findMovementsForMowersQueryMapper.asFindMovementsForMowerFilter(query))
                .map(filter -> findMovementsForMowersQueryMapper.asFullMovement(filter.getPlateau(), filter.getFullMovement()))
                .map(fullMovement -> fullMovement);
    }

    private void moveMowerThroughPlateau(FullMovement fullMovement) {
        this.cantMoveToTheSpecifiedDirection(fullMovement.getPlateau(), fullMovement.getMovement());

    }

    private void cantMoveToTheSpecifiedDirection(Plateau plateau, List<Movement> movement) {

        movement.forEach(movement1 -> {
            Plateau plateau1 = this.putMowerInPlateau(plateau, movement1.getCompassPoint());

        });


    }

    private Plateau putMowerInPlateau(Plateau plateau, CompassPoint compassPoint) {
        plateau.isPositionBusy(compassPoint.getPointX(), compassPoint.getPointY());

        return null;
    }
}
