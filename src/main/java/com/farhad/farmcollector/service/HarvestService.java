package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Harvest;
import com.farhad.farmcollector.data.repository.HarvestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {

    private final HarvestRepository harvestRepository;

    public HarvestService(HarvestRepository harvestRepository) {
        this.harvestRepository = harvestRepository;
    }

    public List<Harvest> getAllHarvests() {
        return harvestRepository.findAll();
    }

    public Harvest saveHarvest(Harvest harvest) {
        return harvestRepository.save(harvest);
    }

    public Harvest getHarvestById(Long id) {
        return harvestRepository.findById(id).orElse(null);
    }
}
