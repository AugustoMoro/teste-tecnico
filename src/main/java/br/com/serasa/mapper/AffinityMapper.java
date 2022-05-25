package br.com.serasa.mapper;

import br.com.serasa.dto.AffinityDTO;
import br.com.serasa.entity.Affinity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AffinityMapper {
    AffinityMapper INSTANCE = Mappers.getMapper(AffinityMapper.class);

    Affinity mapAffinityDTOToAffinityEntity(AffinityDTO affinityDto);

    AffinityDTO mapAffinityEntityToAffinityDto(Affinity affinity);
}
