package br.com.serasa.mock;

import br.com.serasa.dto.PersonDTO;
import br.com.serasa.entity.Person;

public class PersonMocks {
    private static final String PERSON_NAME = "Fulano";
    private static final String PERSON_PHONE_NUMBER = "88999999999";
    private static final Short PERSON_AGE = (short) 30;
    private static final String PERSON_REGION = "sul";
    private static final String PERSON_STATE = "RS";
    private static final String PERSON_CITY = "Santa Maria";
    private static final Long PERSON_SCORE = 500L;

    public static PersonDTO createPersonDtoMock() {
        return PersonDTO.builder()
                .name(PERSON_NAME)
                .phoneNumber(PERSON_PHONE_NUMBER)
                .age(PERSON_AGE)
                .region(PERSON_REGION)
                .state(PERSON_STATE)
                .score(PERSON_SCORE)
                .city(PERSON_CITY)
                .build();
    }

    public static PersonDTO createPersonDtoGetOneMock() {
        return PersonDTO.builder()
                .name(PERSON_NAME)
                .age(PERSON_AGE)
                .phoneNumber(PERSON_PHONE_NUMBER)
                .states(AffinityMocks.createAffinityDTOMock().getStates())
                .scoreDescription(ScoreMocks.createScoreDTOMock().getScoreDescription())
                .build();
    }

    public static PersonDTO createPersonDtoGetAllMock() {
        return PersonDTO.builder()
                .name(PERSON_NAME)
                .state(PERSON_STATE)
                .city(PERSON_CITY)
                .states(AffinityMocks.createAffinityDTOMock().getStates())
                .scoreDescription(ScoreMocks.createScoreDTOMock().getScoreDescription())
                .build();
    }

    public static Person createPersonEntityMock() {
        Person personMock = new Person();
        personMock.setName(PERSON_NAME);
        personMock.setPhoneNumber(PERSON_PHONE_NUMBER);
        personMock.setAge(PERSON_AGE);
        personMock.setRegion(PERSON_REGION);
        personMock.setState(PERSON_STATE);
        personMock.setScore(PERSON_SCORE);
        personMock.setCity(PERSON_CITY);
        return personMock;
    }
}
