package org.jakos176.queries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jakos176.CompassPoint;
import org.jakos176.FullMovement;
import org.jakos176.Movement;
import org.jakos176.Plateau;
import org.jakos176.enums.CompassPointEnum;
import org.jakos176.enums.ControlEnum;
import org.jakos176.exceptions.BusyOrCutPositionException;
import org.jakos176.exceptions.OutOfRangePositionException;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.jakos176.services.ConsoleLogService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
@Slf4j
public class FindMovementsForMowersQueryHandler implements
        QueryHandler<FindMovementsForMowersQuery, Mono<FullMovement>> {

    private final FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper;

    private final ConsoleLogService consoleLogService;

    public Mono<FullMovement> execute(FindMovementsForMowersQuery query) {

        return Mono.just(query)
                .map(filter -> findMovementsForMowersQueryMapper.asFullMovement(filter.getPlateau(), filter.getFullMovement()))
                .doOnNext(fullMovement -> {
                    log.info(fullMovement.getPlateau().getPlateauDistribution().get(1).size() + " " +
                            fullMovement.getPlateau().getPlateauDistribution().get(0).size());
                    fullMovement.getMovement().forEach(movement -> {
                        log.info(movement.getCompassPoint().toString());
                        log.info(movement.getControl().toString());
                    });
                })
                .flatMap(this::moveMowerThroughPlateau)
                .map(fullMovement -> {
                    consoleLogService.showFullMovementInConsole(fullMovement);
                    return fullMovement;
                });
    }

    public Mono<FullMovement> moveMowerThroughPlateau(FullMovement fullMovement) {
        Plateau plateau = fullMovement.getPlateau();
        fullMovement.getMovement().forEach(movement -> {
            Integer pointX = movement.getCompassPoint().getPointX();
            Integer pointY = movement.getCompassPoint().getPointY();

            canMoveToNextPosition(plateau, pointX, pointY);
            AtomicReference<CompassPoint> nexMovement = new AtomicReference<>(movement.getCompassPoint());

            movement.getControl().getControlEnums()
                    .forEach(controlEnum -> {
                        if (controlEnum.equals(ControlEnum.MOVEMENT)) {
                            nexMovement.set(this.guestNexMovement(movement.getCompassPoint().getCompassPointEnum(), pointX, pointY));
                            canMoveToNextPosition(plateau, nexMovement.get().getPointX(), nexMovement.get().getPointY());
                            if (isFirstMovement(fullMovement.getMovement(), movement) || isLastMovement(fullMovement.getMovement())) {
                                plateau.setPositionBusy(nexMovement.get().getPointX(), nexMovement.get().getPointY());
                            } else {
                                plateau.setPositionCut(nexMovement.get().getPointX(), nexMovement.get().getPointY());
                            }

                        } else if (controlEnum.equals(ControlEnum.RIGHT)) {
                            nexMovement.set(this.changeDirectionForMower(ControlEnum.RIGHT, nexMovement.get()));
                        } else {
                            nexMovement.set(this.changeDirectionForMower(ControlEnum.LEFT, nexMovement.get()));
                        }
                    });
            movement.setCompassPoint(nexMovement.get());
        });

        return Mono.just(fullMovement);
    }

    public void canMoveToNextPosition(Plateau plateau, Integer pointX, Integer pointY) {
        if (plateau.positionIsWithInRange(pointX, pointY)) {
            boolean isNonBusy = plateau.setMowerInPlateau(pointX, pointY);
            if (!isNonBusy) {
                throw new BusyOrCutPositionException(pointX, pointY);
            }
        } else {
            throw new OutOfRangePositionException(pointX, pointY);
        }
    }

    public CompassPoint changeDirectionForMower(ControlEnum controlEnum, CompassPoint compassPoint) {
        if (controlEnum.equals(ControlEnum.LEFT)) {
            if (compassPoint.getCompassPointEnum().equals(CompassPointEnum.SOUTH)) {
                compassPoint.setCompassPointEnum(CompassPointEnum.EAST);
            } else if (compassPoint.getCompassPointEnum().equals(CompassPointEnum.NORTH)) {
                compassPoint.setCompassPointEnum(CompassPointEnum.WEST);
            } else if (compassPoint.getCompassPointEnum().equals(CompassPointEnum.WEST)) {
                compassPoint.setCompassPointEnum(CompassPointEnum.SOUTH);
            } else {
                compassPoint.setCompassPointEnum(CompassPointEnum.NORTH);
            }
        } else {
            if (compassPoint.getCompassPointEnum().equals(CompassPointEnum.SOUTH)) {
                compassPoint.setCompassPointEnum(CompassPointEnum.WEST);
            } else if (compassPoint.getCompassPointEnum().equals(CompassPointEnum.NORTH)) {
                compassPoint.setCompassPointEnum(CompassPointEnum.EAST);
            } else if (compassPoint.getCompassPointEnum().equals(CompassPointEnum.WEST)) {
                compassPoint.setCompassPointEnum(CompassPointEnum.NORTH);
            } else {
                compassPoint.setCompassPointEnum(CompassPointEnum.EAST);
            }
        }
        return compassPoint;
    }


    public CompassPoint guestNexMovement(CompassPointEnum compassPointEnum, Integer pointX, Integer pointY) {
        int nextMovementX = pointX + (compassPointEnum == CompassPointEnum.EAST ? 1 : (compassPointEnum == CompassPointEnum.WEST ? -1 : 0));
        int nextMovementY = pointY + (compassPointEnum == CompassPointEnum.NORTH ? 1 : (compassPointEnum == CompassPointEnum.SOUTH ? -1 : 0));

        return CompassPoint.builder()
                .pointX(nextMovementX)
                .pointY(nextMovementY)
                .compassPointEnum(compassPointEnum)
                .build();
    }

    private boolean isFirstMovement(List<Movement> movements, Movement movement) {
        return movements.indexOf(movement) == 0;
    }

    private boolean isLastMovement(List<Movement> movements) {
        Movement lastMovement = movements.get(movements.size() - 1);
        return movements.indexOf(lastMovement) == movements.size();
    }
}
