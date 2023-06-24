package org.jakos176.mapper;

import org.jakos176.CompassPoint;
import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.Plateau;
import org.jakos176.enums.CompassPointEnum;
import org.jakos176.exceptions.InvalidCompassNumericSizePointException;
import org.jakos176.exceptions.InvalidCompassPointException;
import org.jakos176.exceptions.InvalidNumericPlateauException;
import org.jakos176.exceptions.InvalidSizePlateauException;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface FindMovementsForMowersQueryMapper {

    FindMovementsForMowerFilter asFindMovementsForMowerFilter(FindMovementsForMowersQuery findMovementsForMowersQuery);

    FindMovementsForMowersQuery asFindMovementsForMowersQuery(String plateau, List<List<String>> fullMovement);

    default Plateau asPlateau(String plateau) {
        List<Integer> plateauNumbers = getCharacterStream(plateau)
                .filter(s -> s.length() != 0)
                .map(character -> {
                    if (!this.isNumeric(character)) {
                        throw new InvalidNumericPlateauException(character.toCharArray()[0]);
                    }
                    return Integer.valueOf(character);
                }).toList();

        if (plateauNumbers.size() != 2) {
            throw new InvalidSizePlateauException(plateau);
        }

        return Plateau.builder()
                .upperLeft(plateauNumbers.get(0))
                .upperRight(plateauNumbers.get(1))
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
                compassPointNumeric.add(Integer.valueOf(character));
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

    private Stream<String> getCharacterStream(String stringToChar) {
        return Arrays.stream(stringToChar.split(" "));
    }
}