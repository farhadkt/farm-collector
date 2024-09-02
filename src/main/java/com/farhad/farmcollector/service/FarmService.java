package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Farm;
import com.farhad.farmcollector.data.repository.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm saveFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id).orElse(null);
    }
}
