package org.jakos176.mapper;

import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.Plateau;
import org.jakos176.exceptions.InvalidNumericPlateauException;
import org.jakos176.exceptions.InvalidPlateauException;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface FindMovementsForMowersQueryMapper {

    FindMovementsForMowerFilter asFindMovementsForMowerFilter(FindMovementsForMowersQuery findMovementsForMowersQuery);

    FindMovementsForMowersQuery asFindMovementsForMowersQuery(String plateau, List<List<String>> fullMovement);

    default Plateau asPlateau(String plateau) {
        List<Integer> plateauNumbers = plateau.replaceAll("\\s+", "")
                .chars()
                .mapToObj(e -> (char) e)
                .map(this::getNumericFromCharacter).toList();

        if (plateauNumbers.size() != 2) {
            throw new InvalidPlateauException(plateau);
        }

        return Plateau.builder()
                .upperLeft(plateauNumbers.get(0))
                .upperRight(plateauNumbers.get(1))
                .build();
    }

    private Integer getNumericFromCharacter(Character character) {
        if (character != null && character.toString().matches("-?\\d+(\\.\\d+)?")) {
            return Character.getNumericValue(character);
        } else {
            throw new InvalidNumericPlateauException(character);
        }
    }
}