package br.com.api.mapper;

import br.com.api.dto.ScoreDTO;
import br.com.api.entity.Score;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScoreMapper {
    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    Score mapScoreDTOToScoreEntity(ScoreDTO scoreDto);

    ScoreDTO mapScoreEntityToScoreDto(Score score);
}
