package br.com.serasa.service;

import br.com.serasa.dto.ScoreDTO;
import br.com.serasa.entity.Score;
import br.com.serasa.mapper.ScoreMapper;
import br.com.serasa.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public ScoreDTO saveDto(ScoreDTO scoreDto) {
        var scoreEntity = ScoreMapper.INSTANCE.mapScoreDTOToScoreEntity(scoreDto);
        return ScoreMapper.INSTANCE.mapScoreEntityToScoreDto(this.save(scoreEntity));
    }

    public String findScoreDescriptionByScore(Long score) {
        if (score == null) {
            return null;
        }
        return this.findByScore(score).stream()
                .findFirst()
                .map(Score::getScoreDescription)
                .orElse(null);
    }

    Score save(Score score) {
        return this.scoreRepository.save(score);
    }

    List<Score> findByScore(Long score) {
        return this.scoreRepository.findByScoreBetweenMinAndMax(score);
    }


}
