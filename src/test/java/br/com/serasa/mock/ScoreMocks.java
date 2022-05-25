package br.com.serasa.mock;

import br.com.serasa.dto.ScoreDTO;
import br.com.serasa.entity.Score;

public class ScoreMocks {
    private static final String SCORE_DESCRIPTION = "Insuficiente";
    private static final Long SCORE_MIN = 0L;
    private static final Long SCORE_MAX = 200L;

    public static ScoreDTO createScoreDTOMock() {
        return ScoreDTO.builder()
                .scoreDescription(SCORE_DESCRIPTION)
                .min(SCORE_MIN)
                .max(SCORE_MAX)
                .build();
    }

    public static Score createScoreEntityMock() {
        Score scoreMock = new Score();
        scoreMock.setScoreDescription(SCORE_DESCRIPTION);
        scoreMock.setMin(SCORE_MIN);
        scoreMock.setMax(SCORE_MAX);
        return scoreMock;
    }
}
