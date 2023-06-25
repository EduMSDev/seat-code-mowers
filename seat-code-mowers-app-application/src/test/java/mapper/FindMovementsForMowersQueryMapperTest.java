package mapper;

import org.jakos176.CompassPoint;
import org.jakos176.Control;
import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.FullMovement;
import org.jakos176.enums.CompassPointEnum;
import org.jakos176.enums.ControlEnum;
import org.jakos176.exceptions.*;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import queries.QueryObjectMother;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FindMovementsForMowersQueryMapperTest {

    private final FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper = Mappers
            .getMapper(FindMovementsForMowersQueryMapper.class);

    @Test
    void asFindMovementsForMowerFilter_ShouldReturnFindMovementsForMowerFilter_OnSuccess() {
        List<List<String>> fullMovements = List.of(List.of("12N"), List.of("LMLMLMLMM"));
        FindMovementsForMowersQuery findMovementsForMowersQuery = QueryObjectMother
                .findMovementsForMowersQuery("55", fullMovements);

        FindMovementsForMowerFilter findMovementsForMowerFilter = this.findMovementsForMowersQueryMapper
                .asFindMovementsForMowerFilter(findMovementsForMowersQuery);

        assertThat(findMovementsForMowerFilter).usingRecursiveComparison().isEqualTo(findMovementsForMowersQuery);
    }

    @Test
    void asFullMovement_ShouldReturnPlateau_OnSuccess() {
        FullMovement fullMovement = findMovementsForMowersQueryMapper.asFullMovement("5 5", List.of("1 2 E", "LRML"));

        assertThat(fullMovement).returns(0, movement -> movement.getPlateau().getBottomLeft())
                .returns(0, movement -> movement.getPlateau().getBottomRight())
                .returns(5, movement -> movement.getPlateau().getUpperLeft())
                .returns(5, movement -> movement.getPlateau().getUpperRight())
                .returns(1, movement -> movement.getMovement().get(0).getCompassPoint().getPointX())
                .returns(2, movement -> movement.getMovement().get(0).getCompassPoint().getPointY())
                .returns(CompassPointEnum.EAST, movement -> movement.getMovement().get(0).getCompassPoint().getCompassPointEnum())
                .returns(ControlEnum.LEFT, movement -> movement.getMovement().get(0).getControl().getControlEnums().get(0))
                .returns(ControlEnum.RIGHT, movement -> movement.getMovement().get(0).getControl().getControlEnums().get(1))
                .returns(ControlEnum.MOVEMENT, movement -> movement.getMovement().get(0).getControl().getControlEnums().get(2))
                .returns(ControlEnum.LEFT, movement -> movement.getMovement().get(0).getControl().getControlEnums().get(3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5 S", "S 5"})
    void asPlateau_ShouldThrowInvalidPlateau_WhenInputContainsLetter(String invalidPlateau) {
        InvalidPlateauException invalidPlateauException = assertThrows(InvalidPlateauException.class,
                () -> findMovementsForMowersQueryMapper.asPlateau(invalidPlateau));

        assertEquals(1, invalidPlateauException.getApplicationExceptionCode().getCode());

    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "1 1 2", "",})
    void asPlateau_ShouldThrowInvalidSizePlateau_WhenInputStringIsDistinctThatTwo(String invalidPlateau) {
        InvalidSizePlateauException invalidSizePlateauException = assertThrows(InvalidSizePlateauException.class,
                () -> findMovementsForMowersQueryMapper.asPlateau(invalidPlateau));

        assertEquals(2, invalidSizePlateauException.getApplicationExceptionCode().getCode());
    }

    @Test
    void asCompassPoint_ShouldReturnCompassPoint_OnSuccess() {
        CompassPoint compassPoint = findMovementsForMowersQueryMapper.asCompassPoint("1 2 E");

        assertThat(compassPoint).returns(1, CompassPoint::getPointX)
                .returns(2, CompassPoint::getPointY)
                .returns(CompassPointEnum.EAST, CompassPoint::getCompassPointEnum);

    }

    @ParameterizedTest
    @ValueSource(strings = {"10 3 W", "3 10 W", "W 3 10"})
    void asCompassPoint_ShouldThrowInvalidCompassNumericSizePointException_WhenNumericValuesAreMinorThanZeroAndBiggerThan9(
            String invalidCompassPoint) {
        InvalidCompassNumericSizePointException invalidCompassNumericSizePointException = assertThrows(InvalidCompassNumericSizePointException.class,
                () -> findMovementsForMowersQueryMapper.asCompassPoint(invalidCompassPoint));

        assertEquals(4, invalidCompassNumericSizePointException.getApplicationExceptionCode().getCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {"4 3 K", "3 4 Q"})
    void asCompassPoint_ShouldThrowInvalidCompassPointEnumException_WhenCompassPointNonExists(String invalidCompassPoint) {
        InvalidCompassPointEnumException invalidCompassPointEnumException = assertThrows(InvalidCompassPointEnumException.class,
                () -> findMovementsForMowersQueryMapper.asCompassPoint(invalidCompassPoint));

        assertEquals(3, invalidCompassPointEnumException.getApplicationExceptionCode().getCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10 10 3 W", "3 10 W 10"})
    void asCompassPoint_ShouldThrowInvalidCompassPointException_WhenTheInputStringNotComplainTheRequisites(String invalidCompassPoint) {
        InvalidCompassPointException invalidCompassPointException = assertThrows(InvalidCompassPointException.class,
                () -> findMovementsForMowersQueryMapper.asCompassPoint(invalidCompassPoint));

        assertEquals(5, invalidCompassPointException.getApplicationExceptionCode().getCode());
    }

    @Test
    void asControl_ShouldReturnControl_OnSuccess() {
        Control control = findMovementsForMowersQueryMapper.asControl("LRM");

        assertThat(control).returns("L", controlResponse -> controlResponse.getControlEnums().get(0).getDirection())
                .returns("R", controlResponse -> controlResponse.getControlEnums().get(1).getDirection())
                .returns("M", controlResponse -> controlResponse.getControlEnums().get(2).getDirection());

    }

    @ParameterizedTest
    @ValueSource(strings = {"LMR2", "2LMR", "LM2R", "L2MR"})
    void asControl_ShouldThrowInvalidNumericControlException_WhenPassingNumberAsParameter(String invalidCompassPoint) {
        InvalidNumericControlException invalidNumericControlException = assertThrows(InvalidNumericControlException.class,
                () -> findMovementsForMowersQueryMapper.asControl(invalidCompassPoint));

        assertEquals(7, invalidNumericControlException.getApplicationExceptionCode().getCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {"LMRX", "SLMR", "LMHR", "LTMR"})
    void asControl_ShouldThrowInvalidControlException_WhenLetterIsNotSupported(String invalidCompassPoint) {
        InvalidControlException invalidControlException = assertThrows(InvalidControlException.class,
                () -> findMovementsForMowersQueryMapper.asControl(invalidCompassPoint));

        assertEquals(6, invalidControlException.getApplicationExceptionCode().getCode());
    }
}