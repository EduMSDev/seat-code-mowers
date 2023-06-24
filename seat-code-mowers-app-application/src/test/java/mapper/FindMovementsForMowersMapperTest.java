package mapper;

import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.mapper.FindMovementsForMowersMapper;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import queries.QueryObjectMother;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class FindMovementsForMowersMapperTest {

    private final FindMovementsForMowersMapper findMovementsForMowersMapper = Mappers
            .getMapper(FindMovementsForMowersMapper.class);

    @Test
    void asFindMovementsForMowerFilter_OnSuccess() {
        List<List<String>> fullMovements = List.of(List.of("12N"), List.of("LMLMLMLMM"));
        FindMovementsForMowersQuery findMovementsForMowersQuery = QueryObjectMother
                .findMovementsForMowersQuery("55", fullMovements);

        FindMovementsForMowerFilter findMovementsForMowerFilter = this.findMovementsForMowersMapper
                .asFindMovementsForMowerFilter(findMovementsForMowersQuery);

        assertThat(findMovementsForMowerFilter).usingRecursiveComparison().isEqualTo(findMovementsForMowersQuery);
    }
}
