package com.farhad.farmcollector.controller;

import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.service.PlantService;
import com.farhad.farmcollector.service.model.ReportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @PostMapping
    public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) {
        return ResponseEntity.ok(plantService.savePlant(plant));
    }
}
