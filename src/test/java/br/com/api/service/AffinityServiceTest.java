package br.com.api.service;

import br.com.api.mock.AffinityMocks;
import br.com.api.repository.AffinityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AffinityServiceTest {
    @InjectMocks
    AffinityService affinityService;

    @Mock
    AffinityRepository affinityRepository;

    @Test
    void When_SaveDto_Expect_NotNull_And_Equal_To_Mock_AffinityDto() {
        var mockAffinityEntity = AffinityMocks.createAffinityEntityMock();
        var mockAffinityDto = AffinityMocks.createAffinityDTOMock();

        Mockito.when(affinityRepository.save(Mockito.any())).thenReturn(mockAffinityEntity);

        var actualResult = affinityService.saveDto(mockAffinityDto);

        Assertions.assertThat(actualResult).isNotNull().isEqualTo(mockAffinityDto);
    }

    @Test
    void When_FindStatesByRegion_Expect_NotNull_NotEmpty_And_Equal_To_Mock_List_Of_States() {
        var mockStates = AffinityMocks.createAffinityDTOMock().getStates();
        var mockAffinityEntity = Optional.of(AffinityMocks.createAffinityEntityMock());

        Mockito.when(affinityRepository.findById(mockAffinityEntity.get().getRegion())).thenReturn(mockAffinityEntity);

        var actualResult = affinityService.findStatesByRegion(mockAffinityEntity.get().getRegion());

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult).isEqualTo(mockStates);
    }

    @Test
    void When_FindStatesByRegion_Expect_Null_List_Of_States() {
        var mockAffinityEntity = Optional.of(AffinityMocks.createAffinityEntityMock());
        var mockUnexpectedRegion = "Test";

        Mockito.when(affinityRepository.findById(mockAffinityEntity.get().getRegion())).thenReturn(mockAffinityEntity);

        var actualResult = affinityService.findStatesByRegion(mockUnexpectedRegion);

        Assertions.assertThat(actualResult).isNull();
    }

    @Test
    void When_Save_Expect_NotNull_And_Equal_To_Mock_Affinity_Entity() {
        var mockAffinityEntity = AffinityMocks.createAffinityEntityMock();

        Mockito.when(affinityRepository.save(Mockito.any())).thenReturn(mockAffinityEntity);

        var actualResult = affinityService.save(mockAffinityEntity);

        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult).isEqualTo(mockAffinityEntity);
    }

    @Test
    void When_FindByRegion_Expect_NotNull_NotEmpty_And_Equal_To_Mock_Affinity_Entity() {
        var mockAffinityEntity = Optional.of(AffinityMocks.createAffinityEntityMock());

        Mockito.when(affinityRepository.findById(mockAffinityEntity.get().getRegion())).thenReturn(mockAffinityEntity);

        var actualResult = affinityService.findByRegion(mockAffinityEntity.get().getRegion());

        Assertions.assertThat(actualResult).isNotNull().isNotEmpty();
        Assertions.assertThat(actualResult.get()).isEqualTo(mockAffinityEntity.get());
    }
}