package br.com.api.service;

import br.com.api.mock.ScoreMocks;
import br.com.api.repository.ScoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ScoreServiceTest {
    @InjectMocks
    ScoreService scoreService;

    @Mock
    ScoreRepository scoreRepository;


    @Test
    void When_SaveDto_Expect_NotNull_And_Equal_To_Mock_ScoreDto() {
        var mockScoreDto = ScoreMocks.createScoreDTOMock();
        var mockScoreEntity = ScoreMocks.createScoreEntityMock();

        Mockito.when(scoreRepository.save(Mockito.any())).thenReturn(mockScoreEntity);

        var actualResult = scoreService.saveDto(mockScoreDto);

        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult).isEqualTo(mockScoreDto);
    }

    @Test
    void When_FindScoreDescriptionByScore_Expect_NotNull_And_Equal_To_Mock_String() {
        var mockScore = 50L;
        var mockScoreEntity = ScoreMocks.createScoreEntityMock();
        var expectedScoreDescription = mockScoreEntity.getScoreDescription();

        Mockito.when(scoreRepository.findByScoreBetweenMinAndMax(mockScore)).thenReturn(List.of(mockScoreEntity));

        var actualResult = scoreService.findScoreDescriptionByScore(mockScore);

        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult).isEqualTo(expectedScoreDescription);
    }

    @Test
    void When_FindScoreDescriptionByScore_Expect_Null_String() {
        var mockUnexpectedScore = 10_000L;
        var mockScoreEntity = ScoreMocks.createScoreEntityMock();

        Mockito.when(scoreRepository.findByScoreBetweenMinAndMax(0L)).thenReturn(List.of(mockScoreEntity));

        var actualResult = scoreService.findScoreDescriptionByScore(mockUnexpectedScore);

        Assertions.assertThat(actualResult).isNull();
    }

    @Test
    void When_Save_Expect_NotNull_And_Equal_To_Mock_Score_Entity() {
        var mockScoreEntity = ScoreMocks.createScoreEntityMock();

        Mockito.when(scoreRepository.save(Mockito.any())).thenReturn(mockScoreEntity);

        var actualResult = scoreService.save(mockScoreEntity);

        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult).isEqualTo(mockScoreEntity);
    }

    @Test
    void When_FindByScore_Expect_NotNull_NotEmpty_And_Equal_To_Mock_Score_Entity() {
        var mockScore = 50L;
        var mockScoreEntity = List.of(ScoreMocks.createScoreEntityMock());

        Mockito.when(scoreRepository.findByScoreBetweenMinAndMax(mockScore)).thenReturn(mockScoreEntity);

        var actualResult = scoreService.findByScore(mockScore);

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult).isEqualTo(mockScoreEntity);
    }
}