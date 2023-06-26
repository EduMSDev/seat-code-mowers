import org.jakos176.SeatCodeMowersController;
import org.jakos176.dto.FullMovementDto;
import org.jakos176.exceptions.InvalidControlException;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.jakos176.mapper.FullMovementDtoMapper;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.jakos176.queries.FindMovementsForMowersQueryHandler;
import org.jakos176.testmodel.DomainObjectMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeatCodeMowersControllerSeatCodeMowersControllerTest {

    @Mock
    private FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper;

    @Mock
    private FindMovementsForMowersQueryHandler findMovementsForMowersQueryHandler;

    @Mock
    private FullMovementDtoMapper fullMovementDtoMapper;

    @InjectMocks
    private SeatCodeMowersController seatCodeMowersController;

    @Test
    void findMovementsForMowers_ShouldReturnFullMovementDto_OnSuccess() {
        String plateau = "6 6";
        List<String> movements = List.of("1 4 E", "LMR");
        FindMovementsForMowersQuery query = FindMovementsForMowersQuery.builder()
                .build();

        when(findMovementsForMowersQueryMapper.asFindMovementsForMowersQuery(plateau, movements))
                .thenReturn(query);
        when(findMovementsForMowersQueryHandler.execute(query))
                .thenReturn(Mono.just(DomainObjectMother.fullMovement()));

        Mono<ResponseEntity<FullMovementDto>> response = seatCodeMowersController
                .findMovementsForMowers(plateau, movements);
        StepVerifier.create(response)
                .consumeNextWith(responseDto -> {
                    assertThat(responseDto.getStatusCode()).isEqualTo(HttpStatus.OK);
                    assertThat(responseDto.getBody()).isNotNull();
                });
    }

    @Test
    void findMovementsForMowers_ShouldThrowInvalidControlException_WhenControlInputIsNotValidLetter() {
        String plateau = "6 6";
        List<String> movements = List.of("1 4 E", "LMX");
        FindMovementsForMowersQuery query = FindMovementsForMowersQuery.builder()
                .build();

        when(findMovementsForMowersQueryMapper.asFindMovementsForMowersQuery(plateau, movements))
                .thenReturn(query);
        when(findMovementsForMowersQueryHandler.execute(query))
                .thenThrow(new InvalidControlException(movements.get(1)));

        assertThatThrownBy(() -> seatCodeMowersController.findMovementsForMowers(plateau, movements))
                .isInstanceOf(InvalidControlException.class);

    }
}