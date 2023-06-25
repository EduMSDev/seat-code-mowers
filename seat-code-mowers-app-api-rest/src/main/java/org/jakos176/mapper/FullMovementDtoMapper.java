package org.jakos176.mapper;

import org.jakos176.FullMovement;
import org.jakos176.dto.FullMovementDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface FullMovementDtoMapper {

    FullMovementDto asFullMovementDto(FullMovement fullMovement);
}
