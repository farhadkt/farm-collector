package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Harvest;
import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.data.repository.HarvestRepository;
import com.farhad.farmcollector.data.repository.PlantRepository;
import com.farhad.farmcollector.service.model.ReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final HarvestRepository harvestRepository;
    private final PlantRepository plantRepository;

    public ReportService(HarvestRepository harvestRepository, PlantRepository plantRepository) {
        this.harvestRepository = harvestRepository;
        this.plantRepository = plantRepository;
    }

    public List<ReportDTO> getReportBySeason(String season) {
        List<Plant> plants = plantRepository.findAll().stream()
                .filter(plant -> plant.getSeason().equalsIgnoreCase(season))
                .collect(Collectors.toList());

        Map<String, Double> expectedAmounts = plants.stream()
                .collect(Collectors.groupingBy(
                        plant -> plant.getFarm().getName() + ":" + plant.getCropType(),
                        Collectors.summingDouble(Plant::getExpectedProductAmount)
                ));

        Map<String, Double> actualAmounts = harvestRepository.findAll().stream()
                .filter(harvest -> plants.stream()
                        .anyMatch(plant -> plant.getId().equals(harvest.getPlant().getId())))
                .collect(Collectors.groupingBy(
                        harvest -> harvest.getPlant().getFarm().getName() + ":" + harvest.getPlant().getCropType(),
                        Collectors.summingDouble(Harvest::getActualHarvestAmount)
                ));

        return expectedAmounts.entrySet().stream()
                .map(entry -> {
                    String[] keys = entry.getKey().split(":");
                    return new ReportDTO(
                            keys[0], // Farm Name
                            keys[1], // Crop Type
                            season,   // Season
                            entry.getValue(), // Expected Amount
                            actualAmounts.getOrDefault(entry.getKey(), 0.0) // Actual Amount
                    );
                })
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportByCropType(String cropType) {
        List<Plant> plants = plantRepository.findAll().stream()
                .filter(plant -> plant.getCropType().equalsIgnoreCase(cropType))
                .collect(Collectors.toList());

        Map<String, Double> expectedAmounts = plants.stream()
                .collect(Collectors.groupingBy(
                        plant -> plant.getFarm().getName() + ":" + plant.getSeason(),
                        Collectors.summingDouble(Plant::getExpectedProductAmount)
                ));

        Map<String, Double> actualAmounts = harvestRepository.findAll().stream()
                .filter(harvest -> plants.stream()
                        .anyMatch(plant -> plant.getId().equals(harvest.getPlant().getId())))
                .collect(Collectors.groupingBy(
                        harvest -> harvest.getPlant().getFarm().getName() + ":" + harvest.getPlant().getSeason(),
                        Collectors.summingDouble(Harvest::getActualHarvestAmount)
                ));

        return expectedAmounts.entrySet().stream()
                .map(entry -> {
                    String[] keys = entry.getKey().split(":");
                    return new ReportDTO(
                            keys[0], // Farm Name
                            cropType, // Crop Type
                            keys[1], // Season
                            entry.getValue(), // Expected Amount
                            actualAmounts.getOrDefault(entry.getKey(), 0.0) // Actual Amount
                    );
                })
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportForAllSeasons() {
        List<Plant> plants = plantRepository.findAll();
        Map<String, Double> expectedAmounts = plants.stream()
                .collect(Collectors.groupingBy(
                        plant -> plant.getFarm().getName() + ":" + plant.getCropType() + ":" + plant.getSeason(),
                        Collectors.summingDouble(Plant::getExpectedProductAmount)
                ));

        Map<String, Double> actualAmounts = harvestRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        harvest -> harvest.getPlant().getFarm().getName() + ":" + harvest.getPlant().getCropType() + ":" + harvest.getPlant().getSeason(),
                        Collectors.summingDouble(Harvest::getActualHarvestAmount)
                ));

        return expectedAmounts.entrySet().stream()
                .map(entry -> {
                    String[] keys = entry.getKey().split(":");
                    return new ReportDTO(
                            keys[0], // Farm Name
                            keys[1], // Crop Type
                            keys[2], // Season
                            entry.getValue(), // Expected Amount
                            actualAmounts.getOrDefault(entry.getKey(), 0.0) // Actual Amount
                    );
                })
                .collect(Collectors.toList());
    }

}
