package org.jakos176;

import lombok.RequiredArgsConstructor;
import org.jakos176.dto.FullMovementDto;
import org.jakos176.mapper.FindMovementsForMowersQueryMapper;
import org.jakos176.mapper.FullMovementDtoMapper;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.jakos176.queries.FindMovementsForMowersQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/mowers")
@Controller
@RequiredArgsConstructor
public class SeatCodeMowersController {

    private final FindMovementsForMowersQueryMapper findMovementsForMowersQueryMapper;

    private final FindMovementsForMowersQueryHandler findMovementsForMowersQueryHandler;

    private final FullMovementDtoMapper fullMovementDtoMapper;

    @GetMapping(value = "/execute")
    public Mono<ResponseEntity<FullMovementDto>> findMovementsForMowers(@RequestParam String plateau,
                                                                        @RequestParam List<String> fullMovement) {
        FindMovementsForMowersQuery query = findMovementsForMowersQueryMapper.asFindMovementsForMowersQuery(plateau, fullMovement);

        return this.findMovementsForMowersQueryHandler.execute(query)
                .map(this.fullMovementDtoMapper::asFullMovementDto)
                .map(ResponseEntity::ok);
    }
}