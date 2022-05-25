package br.com.api.service;

import br.com.api.dto.AffinityDTO;
import br.com.api.entity.Affinity;
import br.com.api.mapper.AffinityMapper;
import br.com.api.repository.AffinityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AffinityService {
    private final AffinityRepository affinityRepository;

    public AffinityDTO saveDto(AffinityDTO affinityDto) {
        var affinityEntity = AffinityMapper.INSTANCE.mapAffinityDTOToAffinityEntity(affinityDto);
        return AffinityMapper.INSTANCE.mapAffinityEntityToAffinityDto(this.save(affinityEntity));
    }

    public List<String> findStatesByRegion(String region) {
        if (region == null) {
            return null;
        }
        return this.findByRegion(region)
                .map(Affinity::getStates)
                .orElse(null);
    }


    Affinity save(Affinity affinity) {
        return this.affinityRepository.save(affinity);
    }

    Optional<Affinity> findByRegion(String region) {
        return this.affinityRepository.findById(region);
    }
}
