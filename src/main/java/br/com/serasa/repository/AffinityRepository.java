package br.com.serasa.repository;

import br.com.serasa.entity.Affinity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AffinityRepository extends JpaRepository<Affinity, String> {
    @EntityGraph("Affinity.States")
    Optional<Affinity> findById(String region);
}
