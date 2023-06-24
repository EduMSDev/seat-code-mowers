package queries;

import org.jakos176.queries.FindMovementsForMowersQuery;

import java.util.List;

public class QueryObjectMother {

    public static FindMovementsForMowersQuery findMovementsForMowersQuery(String plateau,
                                                                          List<List<String>> fullMovements) {
        return FindMovementsForMowersQuery.builder()
                .plateau(plateau)
                .fullMovement(fullMovements)
                .build();
    }
}
