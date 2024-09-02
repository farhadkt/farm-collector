package com.farhad.farmcollector.controller;

import com.farhad.farmcollector.data.entity.Farm;
import com.farhad.farmcollector.service.FarmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @PostMapping
    public ResponseEntity<Farm> addFarm(@RequestBody Farm farm) {
        return ResponseEntity.ok(farmService.saveFarm(farm));
    }


}
