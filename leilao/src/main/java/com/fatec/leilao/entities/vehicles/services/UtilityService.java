package com.fatec.leilao.entities.vehicles.services;

import com.fatec.leilao.entities.vehicles.Utility;
import com.fatec.leilao.entities.vehicles.dto.UtilityDto;
import com.fatec.leilao.entities.vehicles.repo.UtilityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilityService {

    private final UtilityRepository utilityRepository;

    private final ModelMapper modelMapper;

    public UtilityService(UtilityRepository utilityRepository, ModelMapper modelMapper) {
        this.utilityRepository = utilityRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Utility> findById(Long id) {
        return utilityRepository.findById(id);
    }

    public void save(UtilityDto utility) {
        utilityRepository.save(modelMapper.map(utility, Utility.class));
    }

}
