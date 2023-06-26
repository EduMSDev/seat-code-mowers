package org.jakos176.mapper;

import org.jakos176.FullMovement;
import org.jakos176.dto.FullMovementDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FullMovementDtoMapper {

    FullMovementDto asFullMovementDto(FullMovement fullMovement);
}
