package com.farhad.farmcollector.controller;


import com.farhad.farmcollector.data.entity.Harvest;
import com.farhad.farmcollector.service.HarvestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {
    private final HarvestService harvestService;

    public HarvestController(HarvestService harvestService) {
        this.harvestService = harvestService;
    }

    @GetMapping
    public List<Harvest> getAllHarvests() {
        return harvestService.getAllHarvests();
    }

    @PostMapping
    public ResponseEntity<Harvest> addHarvest(@RequestBody Harvest harvest) {
        return ResponseEntity.ok(harvestService.saveHarvest(harvest));
    }
}
