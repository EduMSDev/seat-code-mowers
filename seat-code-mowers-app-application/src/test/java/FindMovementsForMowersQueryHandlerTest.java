import org.jakos176.CompassPoint;
import org.jakos176.Plateau;
import org.jakos176.enums.CompassPointEnum;
import org.jakos176.enums.ControlEnum;
import org.jakos176.exceptions.BusyOrCutPositionException;
import org.jakos176.exceptions.OutOfRangePositionException;
import org.jakos176.queries.FindMovementsForMowersQueryHandler;
import org.jakos176.testmodel.DomainObjectMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class FindMovementsForMowersQueryHandlerTest {

    @InjectMocks
    private FindMovementsForMowersQueryHandler findMovementsForMowersQueryHandler;

    private static Stream<Arguments> correctMovements() {
        Plateau plateau = DomainObjectMother.plateau();
        return Stream.of(
                arguments(plateau, 3, 5),
                arguments(plateau, 2, 4),
                arguments(plateau, 1, 3),
                arguments(plateau, 3, 4)
        );
    }

    private static Stream<Arguments> incorrectMovements() {
        Plateau plateau = DomainObjectMother.plateau();
        return Stream.of(
                arguments(plateau, 3, 6),
                arguments(plateau, 0, 7),
                arguments(plateau, 9, 2),
                arguments(plateau, 10, 10)
        );
    }


    @ParameterizedTest
    @MethodSource("correctMovements")
    void canMoveToNextPosition_ShouldReturnOk_WhenCanMoveToNextPosition(Plateau plateau, Integer posX, Integer posY) {
        findMovementsForMowersQueryHandler.canMoveToNextPosition(plateau, posX, posY);
    }

    @ParameterizedTest
    @MethodSource("correctMovements")
    void canMoveToNextPosition_ShouldThrowBusyOrCutException_WhenPositionIsCutOrBusy(Plateau plateau, Integer posX, Integer posY) {
        plateau.getPlateauDistribution()
                .forEach(cells -> cells
                        .forEach(cell -> cell.setBusy(true)));

        BusyOrCutPositionException busyOrCutPositionException = assertThrows(BusyOrCutPositionException.class,
                () -> findMovementsForMowersQueryHandler.canMoveToNextPosition(plateau, posX, posY));

        assertEquals(8, busyOrCutPositionException.getApplicationExceptionCode().getCode());
    }

    @ParameterizedTest
    @MethodSource("incorrectMovements")
    void canMoveToNextPosition_ShouldThrowOutOfRangePositionException_WhenPositionIsOutOfRange(Plateau plateau, Integer posX, Integer posY) {
        OutOfRangePositionException outOfRangePositionException = assertThrows(OutOfRangePositionException.class,
                () -> findMovementsForMowersQueryHandler.canMoveToNextPosition(plateau, posX, posY));

        assertEquals(9, outOfRangePositionException.getApplicationExceptionCode().getCode());
    }

    @Test
    void changeDirectionForMower_ShouldReturnEast_WhenCompassPointIsSouthAndDirectionLeft() {
        CompassPoint compassPoint = CompassPoint.builder().compassPointEnum(CompassPointEnum.SOUTH).build();

        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .changeDirectionForMower(ControlEnum.LEFT, compassPoint);

        assertEquals(CompassPointEnum.EAST, compassPointExpected.getCompassPointEnum());
    }

    @Test
    void changeDirectionForMower_ShouldReturnWest_WhenCompassPointIsNorthAndDirectionLeft() {
        CompassPoint compassPoint = CompassPoint.builder().compassPointEnum(CompassPointEnum.NORTH).build();

        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .changeDirectionForMower(ControlEnum.LEFT, compassPoint);

        assertEquals(CompassPointEnum.WEST, compassPointExpected.getCompassPointEnum());
    }

    @Test
    void changeDirectionForMower_ShouldReturnSouth_WhenCompassPointIsWestAndDirectionLeft() {
        CompassPoint compassPoint = CompassPoint.builder().compassPointEnum(CompassPointEnum.WEST).build();

        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .changeDirectionForMower(ControlEnum.LEFT, compassPoint);

        assertEquals(CompassPointEnum.SOUTH, compassPointExpected.getCompassPointEnum());
    }


    @Test
    void changeDirectionForMower_ShouldReturnNorth_WhenCompassPointIsEastAndDirectionLeft() {
        CompassPoint compassPoint = CompassPoint.builder()
                .compassPointEnum(CompassPointEnum.NORTH)
                .build();

        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .changeDirectionForMower(ControlEnum.LEFT, compassPoint);

        assertEquals(CompassPointEnum.WEST, compassPointExpected.getCompassPointEnum());
    }

    @Test
    void guestNexMovement_ShouldReturnPointXPlusOne_WhenCompassPointIsEast() {
        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .guestNexMovement(CompassPointEnum.EAST, 2, 3);

        assertEquals(3, compassPointExpected.getPointX());
    }

    @Test
    void guestNexMovement_ShouldReturnPointXMinusOne_WhenCompassPointIsWest() {
        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .guestNexMovement(CompassPointEnum.WEST, 2, 3);

        assertEquals(1, compassPointExpected.getPointX());
    }


    @Test
    void guestNexMovement_ShouldReturnPointYPlusOne_WhenCompassPointIsNorth() {
        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .guestNexMovement(CompassPointEnum.NORTH, 2, 3);

        assertEquals(4, compassPointExpected.getPointY());
    }

    @Test
    void guestNexMovement_ShouldReturnPointYMinusOne_WhenCompassPointIsSouth() {
        CompassPoint compassPointExpected = findMovementsForMowersQueryHandler
                .guestNexMovement(CompassPointEnum.SOUTH, 2, 3);

        assertEquals(2, compassPointExpected.getPointY());
    }
}