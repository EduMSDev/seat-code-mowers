package org.jakos176.mapper;

import org.jakos176.*;
import org.jakos176.enums.CompassPointEnum;
import org.jakos176.enums.ControlEnum;
import org.jakos176.exceptions.*;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FindMovementsForMowersQueryMapper {

    FindMovementsForMowerFilter asFindMovementsForMowerFilter(FindMovementsForMowersQuery findMovementsForMowersQuery);

    FindMovementsForMowersQuery asFindMovementsForMowersQuery(String plateau, List<String> fullMovement);

    default Plateau asPlateau(String plateau) {
        List<Integer> plateauNumbers = getCharacterStream(plateau)
                .filter(s -> s.length() != 0)
                .map(character -> {
                    if (!this.isNumeric(character)) {
                        throw new InvalidPlateauException(character.toCharArray()[0]);
                    }
                    return Integer.valueOf(character);
                }).toList();

        if (plateauNumbers.size() != 2) {
            throw new InvalidSizePlateauException(plateau);
        }

        List<Integer> axis = getCharacterStream(plateau)
                .map(Integer::valueOf).toList();
        
        List<List<Cell>> board = new ArrayList<>(axis.get(0));
        for (int i = 0; i < axis.get(0); i++) {
            List<Cell> row = new ArrayList<>(axis.get(0));
            for (int j = 0; j < axis.get(0); j++) {
                Cell cell = Cell.builder()
                        .pointX(i)
                        .pointY(j).build();
                row.add(cell);
            }
            board.add(row);
        }

        return Plateau.builder()
                .plateauDistribution(board)
                .build();
    }

    private boolean isNumeric(String character) {
        return character != null && character.matches("-?\\d+(\\.\\d+)?");
    }

    default CompassPoint asCompassPoint(String compassPoint) {
        List<String> compassPointEnum = new ArrayList<>();
        List<Integer> compassPointNumeric = new ArrayList<>();

        getCharacterStream(compassPoint).forEach(character -> {
            if (this.isNumeric(character)) {
                compassPointNumeric.add(Integer.parseInt(character) + 1);
            } else {
                compassPointEnum.add(character);
            }
        });

        if (compassPointEnum.size() != 1 || compassPointNumeric.size() != 2) {
            throw new InvalidCompassPointException(compassPoint);
        }

        compassPointNumeric.forEach(integer -> {
            if (integer < 0 || integer > 9) {
                throw new InvalidCompassNumericSizePointException(integer);
            }
        });
        CompassPointEnum controlEnum = CompassPointEnum.asCompassPoint(compassPointEnum.get(0));

        return CompassPoint.builder()
                .compassPointEnum(controlEnum)
                .pointX(compassPointNumeric.get(0))
                .pointY(compassPointNumeric.get(1))
                .build();
    }

    default Control asControl(String control) {
        List<ControlEnum> controlEnumList = control.chars()
                .mapToObj(value -> (char) value)
                .map(String::valueOf)
                .peek(charAsString -> {
                    if (this.isNumeric(charAsString)) {
                        throw new InvalidNumericControlException(charAsString);
                    }
                })
                .map(ControlEnum::asControlEnum)
                .toList();

        return Control.builder()
                .controlEnums(controlEnumList)
                .build();
    }

    default FullMovement asFullMovement(String plateauString, List<String> movements) {
        Plateau plateau = asPlateau(plateauString);
        List<String> modifiedMovements = new ArrayList<>();
        movements.forEach(movement -> modifiedMovements.add(movement.replace("\"", "")));
        List<Movement> list = IntStream.range(0, movements.size())
                .mapToObj(i -> i % 2 == 0
                        ? Movement.builder()
                        .compassPoint(asCompassPoint(modifiedMovements.get(i)))
                        .control(asControl(modifiedMovements.get(i + 1)))
                        .build()
                        : null)
                .filter(Objects::nonNull)
                .toList();

        return FullMovement.builder()
                .plateau(plateau)
                .movement(list)
                .build();
    }

    private Stream<String> getCharacterStream(String stringToChar) {
        String s = stringToChar.replace("\"", "");
        return Arrays.stream(s.split(" "));
    }
}