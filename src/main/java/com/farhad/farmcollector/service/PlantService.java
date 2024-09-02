package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.data.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant savePlant(Plant plant) {
        return plantRepository.save(plant);
    }
}
