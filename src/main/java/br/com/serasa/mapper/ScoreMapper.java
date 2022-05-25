package br.com.serasa.mapper;

import br.com.serasa.dto.ScoreDTO;
import br.com.serasa.entity.Score;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScoreMapper {
    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    Score mapScoreDTOToScoreEntity(ScoreDTO scoreDto);

    ScoreDTO mapScoreEntityToScoreDto(Score score);
}
