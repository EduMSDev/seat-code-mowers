package org.jakos176.testmodel;

import org.jakos176.*;
import org.jakos176.enums.CompassPointEnum;
import org.jakos176.enums.ControlEnum;

import java.util.ArrayList;
import java.util.List;

public class DomainObjectMother {

    public static Plateau.PlateauBuilder plateauBase() {
        return Plateau.builder()
                .plateauDistribution(getBasicCells());
    }

    public static Plateau plateau() {
        return plateauBase().build();
    }

    public static Plateau plateauWithNonBusyAndCutCell() {
        return plateauBase()
                .plateauDistribution(getBasicCells())
                .build();
    }

    private static List<List<Cell>> getBasicCells() {
        int size = 5;

        List<List<Cell>> board = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                Cell cell = Cell.builder()
                        .pointX(i)
                        .pointY(j).build();
                row.add(cell);
            }
            board.add(row);
        }

        return board;
    }

    public static FullMovement fullMovement() {
        return FullMovement.builder()
                .plateau(plateau())
                .movement(movements())
                .build();
    }

    public static List<Movement> movements() {
        Movement movement = Movement.builder()
                .compassPoint(compassPoint())
                .control(control())
                .build();

        return List.of(movement);
    }

    public static CompassPoint compassPoint() {
        return CompassPoint.builder()
                .compassPointEnum(CompassPointEnum.EAST)
                .pointX(1)
                .pointY(4)
                .build();
    }

    public static Control control() {
        List<ControlEnum> controlEnums = List.of(ControlEnum.LEFT, ControlEnum.MOVEMENT, ControlEnum.RIGHT);
        return Control.builder()
                .controlEnums(controlEnums)
                .build();
    }
}
