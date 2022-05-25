package br.com.api.mapper;

import br.com.api.dto.AffinityDTO;
import br.com.api.entity.Affinity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AffinityMapper {
    AffinityMapper INSTANCE = Mappers.getMapper(AffinityMapper.class);

    Affinity mapAffinityDTOToAffinityEntity(AffinityDTO affinityDto);

    AffinityDTO mapAffinityEntityToAffinityDto(Affinity affinity);
}
