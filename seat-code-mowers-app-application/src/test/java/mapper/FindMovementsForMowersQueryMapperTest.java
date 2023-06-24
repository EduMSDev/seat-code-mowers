package mapper;

import org.jakos176.CompassPoint;
import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.Plateau;
import org.jakos176.enums.CompassPointEnum;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FindMovementsForMowersQueryMapperTest {

    private final FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper = Mappers
            .getMapper(FindMovementsForMowersQueryMapper.class);

    @Test
    void asFindMovementsForMowerFilter_OnSuccess() {
        List<List<String>> fullMovements = List.of(List.of("12N"), List.of("LMLMLMLMM"));
        FindMovementsForMowersQuery findMovementsForMowersQuery = QueryObjectMother
                .findMovementsForMowersQuery("55", fullMovements);

        FindMovementsForMowerFilter findMovementsForMowerFilter = this.findMovementsForMowersQueryMapper
                .asFindMovementsForMowerFilter(findMovementsForMowersQuery);

        assertThat(findMovementsForMowerFilter).usingRecursiveComparison().isEqualTo(findMovementsForMowersQuery);
    }

    @Test
    void asPlateau_shouldReturnPlateau_OnSuccess() {
        Plateau plateau = findMovementsForMowersQueryMapper.asPlateau("5 5");
        Plateau plateauExpected = QueryObjectMother.plateau();

        assertThat(plateau).returns(0, Plateau::getBottomLeft)
                .returns(0, Plateau::getBottomRight)
                .returns(5, Plateau::getUpperLeft)
                .returns(5, Plateau::getUpperRight);

    }

    @ParameterizedTest
    @ValueSource(strings = {"5 S", "S 5"})
    void asPlateau_shouldThrowInvalidPlateau_WhenInputContainsLetter(String invalidPlateau) {
        assertThrows(InvalidNumericPlateauException.class, () -> {
            findMovementsForMowersQueryMapper.asPlateau(invalidPlateau);
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "1 1 2", "",})
    void asPlateau_shouldThrowInvalidPlateau_WhenInputStringIsDistinctThatTwo(String invalidPlateau) {
        assertThrows(InvalidSizePlateauException.class, () -> {
            findMovementsForMowersQueryMapper.asPlateau(invalidPlateau);
        });
    }

    @Test
    void asCompassPoint_shouldReturnCompassPoint_OnSuccess() {
        CompassPoint compassPoint = findMovementsForMowersQueryMapper.asCompassPoint("1 2 E");

        assertThat(compassPoint).returns(1, CompassPoint::getPointX)
                .returns(2, CompassPoint::getPointY)
                .returns(CompassPointEnum.EAST, CompassPoint::getCompassPointEnum);

    }

    @ParameterizedTest
    @ValueSource(strings = {"10 3 W", "3 10 W", "W 3 10"})
    void asCompassPoint_shouldThrowInvalidCompassNumericSizePointException_WhenNumericValuesAreMinorThanZeroAndBiggerThan9(
            String invalidCompassPoint) {
        assertThrows(InvalidCompassNumericSizePointException.class, () -> {
            findMovementsForMowersQueryMapper.asCompassPoint(invalidCompassPoint);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"4 3 K", "3 4 Q"})
    void asCompassPoint_InvalidCompassPointEnumException_WhenCompassPointNonExists(String invalidCompassPoint) {
        assertThrows(InvalidCompassPointEnumException.class, () -> {
            findMovementsForMowersQueryMapper.asCompassPoint(invalidCompassPoint);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"10 10 3 W", "3 10 W 10"})
    void asCompassPoint_InvalidCompassPointException_WhenTheInputStringNotComplainTheRequisites(String invalidCompassPoint) {
        assertThrows(InvalidCompassPointException.class, () -> {
            findMovementsForMowersQueryMapper.asCompassPoint(invalidCompassPoint);
        });
    }
}
