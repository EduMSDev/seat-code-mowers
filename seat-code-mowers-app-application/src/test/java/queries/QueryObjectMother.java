package queries;

import org.jakos176.Plateau;
import org.jakos176.queries.FindMovementsForMowersQuery;

import java.util.List;

public class QueryObjectMother {

    public static FindMovementsForMowersQuery findMovementsForMowersQuery(String plateau, List<String> fullMovements) {
        return FindMovementsForMowersQuery.builder()
                .plateau(plateau)
                .fullMovement(fullMovements)
                .build();
    }

    public static Plateau plateau() {
        return Plateau.builder()
//                .bottomRight(0)
//                .bottomLeft(0)
//                .upperLeft(5)
//                .upperRight(5)
                .build();
    }
}
