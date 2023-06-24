package org.jakos176;

import lombok.RequiredArgsConstructor;
import org.jakos176.dto.FullMovementDto;
import org.jakos176.mapper.FindMovementsForMowersMapper;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.jakos176.queries.FindMovementsForMowersQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/mowers")
@RestController
@RequiredArgsConstructor
public class SeatCodeMowersController {

    private final FindMovementsForMowersMapper findMovementsForMowersMapper;

    private final FindMovementsForMowersQueryHandler findMovementsForMowersQueryHandler;

    @GetMapping("/execute")
    public Mono<ResponseEntity<FullMovementDto>> findMovementsForMowers(String plateau, List<List<String>> fullMovement) {
        FindMovementsForMowersQuery query = findMovementsForMowersMapper.asFindMovementsForMowersQuery(plateau, fullMovement);

//        return this.findMovementsForMowersQueryHandler.execute(query);
        return null;
    }
}