package model;

import org.jakos176.FindMovementsForMowerFilter;

import java.util.List;

public class DomainObjectMother {

    public static FindMovementsForMowerFilter findMovementsForMowerFilter() {
        List<List<String>> fullMovements = List.of(List.of("12N"), List.of("LMLMLMLMM"));
        return FindMovementsForMowerFilter.builder()
                .plateau("5 5")
                .fullMovement(fullMovements)
                .build();
    }
}
