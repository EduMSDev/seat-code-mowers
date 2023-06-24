package org.jakos176.mapper;

import org.jakos176.FindMovementsForMowerFilter;
import org.jakos176.queries.FindMovementsForMowersQuery;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface FindMovementsForMowersMapper {

    FindMovementsForMowerFilter asFindMovementsForMowerFilter(FindMovementsForMowersQuery findMovementsForMowersQuery);

    FindMovementsForMowersQuery asFindMovementsForMowersQuery(String plateau, List<List<String>> fullMovement);
}