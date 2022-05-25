package br.com.serasa.service;

import br.com.serasa.mock.PersonMocks;
import br.com.serasa.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Mock
    AffinityService affinityService;

    @Mock
    ScoreService scoreService;

    @Test
    void When_SaveDto_Expect_NotNull_And_Equal_To_Mock_Name_PersonDto() {
        var mock = PersonMocks.createPersonDtoMock();

        Mockito.when(personRepository.save(Mockito.any())).thenReturn(PersonMocks.createPersonEntityMock());

        var actualResult = personService.saveDto(mock);

        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult.getName()).isEqualTo(mock.getName());
    }

    @Test
    void When_FindPersonDtoById_Expect_NotNull_NotEmpty_And_Equal_To_Mock_PersonDto() {
        var mockId = 1L;
        var mockPersonDto = PersonMocks.createPersonDtoGetOneMock();
        var mockPersonEntity = Optional.of(PersonMocks.createPersonEntityMock());

        Mockito.when(personRepository.findById(mockId)).thenReturn(mockPersonEntity);
        Mockito.when(affinityService.findStatesByRegion(mockPersonEntity.get().getRegion())).thenReturn(mockPersonDto.getStates());
        Mockito.when(scoreService.findScoreDescriptionByScore(mockPersonEntity.get().getScore())).thenReturn(mockPersonDto.getScoreDescription());

        var actualResult = personService.findPersonDtoById(mockId);

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult.get()).isEqualTo(mockPersonDto);
    }

    @Test
    void When_FindAllPersonDto_Expect_NotNull_NotEmpty_And_Equal_To_Mock_List_Of_PersonDto() {
        var mockPersonListDto = List.of(PersonMocks.createPersonDtoGetAllMock());
        var mockPersonListEntity = List.of(PersonMocks.createPersonEntityMock());
        var mockPersonEntity = mockPersonListEntity.get(0);
        var mockPersonDto = mockPersonListDto.get(0);

        Mockito.when(personRepository.findAll()).thenReturn(mockPersonListEntity);
        Mockito.when(affinityService.findStatesByRegion(mockPersonEntity.getRegion())).thenReturn(mockPersonDto.getStates());
        Mockito.when(scoreService.findScoreDescriptionByScore(mockPersonEntity.getScore())).thenReturn(mockPersonDto.getScoreDescription());

        var actualResult = personService.findAllPersonsDto();

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult.get()).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult.get()).isEqualTo(mockPersonListDto);
    }

    @Test
    void When_Save_Expect_NotNull_And_Equal_To_Mock_Person_Entity() {
        var mock = PersonMocks.createPersonEntityMock();
        var expected = PersonMocks.createPersonEntityMock();
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(expected);

        var actualResult = personService.save(mock);

        Assertions.assertThat(actualResult).isNotNull().isEqualTo(expected);
    }

    @Test
    void When_FindById_Expect_NotNull_NotEmpty_And_Equal_To_Mock_Person_Entity() {
        var mock = 1L;
        var expected = Optional.of(PersonMocks.createPersonEntityMock());
        Mockito.when(personRepository.findById(mock)).thenReturn(expected);

        var actualResult = personService.findById(mock);

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult.get()).isNotNull().isEqualTo(expected.get());
    }

    @Test
    void When_FindAll_Expect_NotNull_NotEmpty_Size_Equal_To_1_And_Equal_To_Mock_List_Of_PersonDto() {
        var expected = List.of(PersonMocks.createPersonEntityMock());
        Mockito.when(personRepository.findAll()).thenReturn(expected);

        var actualResult = personService.findAll();

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult.size()).isEqualTo(1);
        Assertions.assertThat(actualResult).isEqualTo(expected);
    }
}