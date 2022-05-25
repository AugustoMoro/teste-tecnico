package br.com.serasa.repository;

import br.com.serasa.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, String> {
    @Query("SELECT s FROM Score s WHERE ?1 BETWEEN s.min AND s.max")
    List<Score> findByScoreBetweenMinAndMax(Long score);
}
