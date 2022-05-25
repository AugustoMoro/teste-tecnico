package br.com.serasa.mock;

import br.com.serasa.dto.AffinityDTO;
import br.com.serasa.entity.Affinity;

import java.util.List;

public class AffinityMocks {
    private static final String AFFINITY_REGION = "sul";
    private static final List<String> AFFINITY_STATES = List.of("RS", "SC", "PR");
    public static AffinityDTO createAffinityDTOMock() {
        return  AffinityDTO.builder()
                .region(AFFINITY_REGION)
                .states(AFFINITY_STATES)
                .build();
    }

    public static Affinity createAffinityEntityMock() {
        Affinity affinityMock = new Affinity();
        affinityMock.setRegion(AFFINITY_REGION);
        affinityMock.setStates(AFFINITY_STATES);
        return affinityMock;
    }
}
