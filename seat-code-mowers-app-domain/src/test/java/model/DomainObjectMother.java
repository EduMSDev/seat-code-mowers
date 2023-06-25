package model;

import org.jakos176.Cell;
import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.Plateau;

import java.util.ArrayList;
import java.util.List;

public class DomainObjectMother {

    public static FindMovementsForMowerFilter findMovementsForMowerFilter() {
        List<String> fullMovements = List.of("12N", "LMLMLMLMM");
        return FindMovementsForMowerFilter.builder()
                .plateau("5 5")
                .fullMovement(fullMovements)
                .build();
    }

    public static Plateau.PlateauBuilder plateauBase() {
        List<Cell> cells = getBasicCells();
        return Plateau.builder()
                .plateauDistribution(List.of(cells, cells));
    }

    public static Plateau plateau() {
        return plateauBase().build();
    }

    public static Plateau plateauWithBusyAndCutCell() {
        Cell cell = cell(true, true);
        return plateauBase()
                .plateauDistribution(List.of(List.of(cell)))
                .build();
    }

    public static Plateau plateauWithNonBusyAndCutCell() {
        Cell cell = cell(false, false);
        return plateauBase()
                .plateauDistribution(List.of(List.of(cell)))
                .build();
    }

    private static Cell cell(Boolean cut, Boolean busy) {
        return Cell.builder()
                .isCut(cut)
                .isBusy(busy)
                .build();
    }

    private static List<Cell> getBasicCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cells.add(Cell.builder().build());
        }
        return cells;
    }
}
