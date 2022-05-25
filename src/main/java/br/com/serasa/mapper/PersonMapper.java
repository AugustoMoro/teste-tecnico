package br.com.serasa.mapper;

import br.com.serasa.dto.PersonDTO;
import br.com.serasa.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person mapPersonDtoToPersonEntity(PersonDTO personDto);

    @Mapping(source = "city", target = "city", ignore = true)
    @Mapping(source = "state", target = "state", ignore = true)
    @Mapping(source = "region", target = "region", ignore = true)
    @Mapping(source = "score", target = "score", ignore = true)
    @Mapping(source = "id", target = "id", ignore = true)
    PersonDTO mapPersonEntityToPersonDtoGetOne(Person person);

    @Mapping(source = "phoneNumber", target = "phoneNumber", ignore = true)
    @Mapping(source = "age", target = "age", ignore = true)
    @Mapping(source = "region", target = "region", ignore = true)
    @Mapping(source = "score", target = "score", ignore = true)
    @Mapping(source = "id", target = "id", ignore = true)
    PersonDTO mapPersonEntityToPersonDtoGetAll(Person person);
}
