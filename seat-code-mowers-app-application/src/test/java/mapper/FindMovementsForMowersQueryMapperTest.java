package mapper;

import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.Plateau;
import org.jakos176.exceptions.InvalidNumericPlateauException;
import org.jakos176.exceptions.InvalidPlateauException;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    void asPlateau_shouldThrowInvalidPlateau_WhenInputContainsLetter() {
        assertThrows(InvalidNumericPlateauException.class, () -> {
            findMovementsForMowersQueryMapper.asPlateau("5 S");
        });

    }

    @Test
    void asPlateau_shouldThrowInvalidNumericPlateau_WhenStringSizeIsDistinctThatTwo() {
        assertThrows(InvalidPlateauException.class, () -> {
            findMovementsForMowersQueryMapper.asPlateau("5 5 4");
        });
    }
}
