package br.com.serasa.service;

import br.com.serasa.dto.PersonDTO;
import br.com.serasa.entity.Person;
import br.com.serasa.mapper.PersonMapper;
import br.com.serasa.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final AffinityService affinityService;
    private final ScoreService scoreService;

    public PersonDTO saveDto(PersonDTO personDto) {
        var personEntity = PersonMapper.INSTANCE.mapPersonDtoToPersonEntity(personDto);
        return PersonMapper.INSTANCE.mapPersonEntityToPersonDtoGetOne(this.save(personEntity));
    }

    public Optional<PersonDTO> findPersonDtoById(Long id) {
        var optionalPerson = this.findById(id);

        if(optionalPerson.isEmpty()) {
            return Optional.empty();
        }

        var personDto = this.processPersonDtoGetOne(optionalPerson.get());
        return Optional.of(personDto);
    }

    public Optional<List<PersonDTO>> findAllPersonsDto() {
        var persons = this.findAll();
        if(persons == null || persons.isEmpty()) {
            return Optional.empty();
        }
        var personsDto = new ArrayList<PersonDTO>();
        for(var person: persons) {
            var personDto = this.processPersonDtoGetAll(person);
            personsDto.add(personDto);
        }
        return Optional.of(personsDto);
    }

    Person save(Person person) {
        return this.personRepository.save(person);
    }

    Optional<Person> findById(Long id) {
        return this.personRepository.findById(id);
    }

    List<Person> findAll() {
        return this.personRepository.findAll();
    }

    private PersonDTO processPersonDtoGetOne(Person person) {
        var personDto = PersonMapper.INSTANCE.mapPersonEntityToPersonDtoGetOne(person);
        return this.processPersonDto(personDto, person);
    }

    private PersonDTO processPersonDtoGetAll(Person person) {
        var personDto = PersonMapper.INSTANCE.mapPersonEntityToPersonDtoGetAll(person);
        return this.processPersonDto(personDto, person);
    }

    private PersonDTO processPersonDto(PersonDTO personDto, Person personEntity) {
        personDto.setStates(affinityService.findStatesByRegion(personEntity.getRegion()));
        personDto.setScoreDescription(scoreService.findScoreDescriptionByScore(personEntity.getScore()));
        return personDto;
    }
}
