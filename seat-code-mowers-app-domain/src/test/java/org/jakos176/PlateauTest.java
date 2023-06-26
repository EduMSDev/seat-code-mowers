package org.jakos176;

import org.jakos176.testmodel.DomainObjectMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class PlateauTest {

    private static Stream<Arguments> correctPosition() {
        return Stream.of(
                arguments(3, 4),
                arguments(1, 2),
                arguments(5, 2),
                arguments(3, 1)
        );
    }

    private static Stream<Arguments> incorrectPosition() {
        return Stream.of(
                arguments(6, 8),
                arguments(4, 9),
                arguments(7, 4)
        );
    }

    @MethodSource("correctPosition")
    @ParameterizedTest
    void positionIsWithInRange_ShouldReturnTrue_WhenPositionIsCorrect(Integer posX, Integer posY) {
        Plateau plateau = DomainObjectMother.plateau();

        assertTrue(plateau.positionIsWithInRange(posX, posY));
    }

    @MethodSource("incorrectPosition")
    @ParameterizedTest
    void positionIsWithInRange_ShouldReturnFalse_WhenPositionIsIncorrect(Integer posX, Integer posY) {
        Plateau plateau = DomainObjectMother.plateau();

        assertFalse(plateau.positionIsWithInRange(posX, posY));
    }

    @Test
    void setMowerInPlateau_ShouldReturnTrue_WhenCellIsNonCutAndBusy() {
        Plateau plateau = DomainObjectMother.plateauWithNonBusyAndCutCell();

        assertTrue(plateau.setMowerInPlateau(0, 0));
    }
}
