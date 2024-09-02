package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Farm;
import com.farhad.farmcollector.data.entity.Harvest;
import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.data.repository.HarvestRepository;
import com.farhad.farmcollector.data.repository.PlantRepository;
import com.farhad.farmcollector.service.model.ReportDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    @Mock
    private HarvestRepository harvestRepository;

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReportBySeason() {
        Farm farmA = new Farm(1L, "FarmA");
        String season = "Winter";
        Plant plant1 = new Plant(1L, farmA, "Field1", season, 10.0, "Corn", 100.0);
        Plant plant2 = new Plant(2L, farmA, "Field2", "Summer", 15.0, "Potatoes", 150.0);
        Harvest harvest1 = new Harvest(1L, 80.0, plant1);
        Harvest harvest2 = new Harvest(2L, 140.0, plant2);

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));
        when(harvestRepository.findAll()).thenReturn(Arrays.asList(harvest1, harvest2));

        List<ReportDTO> reports = reportService.getReportBySeason(season);
        assertEquals(1, reports.size());
        assertEquals(season, reports.get(0).getSeason());
    }

    @Test
    public void testGetReportByCropType() {
        Farm farmA = new Farm(1L, "FarmA");
        String cropType = "Corn";
        Plant plant1 = new Plant(1L, farmA, "Field1", "Winter", 10.0, cropType, 100.0);
        Plant plant2 = new Plant(2L, farmA, "Field2", "Summer", 15.0, "Potatoes", 120.0);
        Harvest harvest1 = new Harvest(1L, 80.0, plant1);
        Harvest harvest2 = new Harvest(2L, 110.0, plant2);

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));
        when(harvestRepository.findAll()).thenReturn(Arrays.asList(harvest1, harvest2));

        List<ReportDTO> reports = reportService.getReportByCropType(cropType);
        assertEquals(1, reports.size());
        assertEquals(cropType, reports.get(0).getCropType());
    }

    @Test
    public void testGetReportForAllSeasons() {
        Farm farmA = new Farm(1L, "FarmA");
        Plant plant1 = new Plant(1L, farmA, "Field1", "Winter", 10.0, "Corn", 100.0);
        Plant plant2 = new Plant(2L, farmA, "Field2", "Summer", 15.0, "Corn", 120.0);
        Harvest harvest1 = new Harvest(1L, 80.0, plant1);
        Harvest harvest2 = new Harvest(2L, 110.0, plant2);

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));
        when(harvestRepository.findAll()).thenReturn(Arrays.asList(harvest1, harvest2));

        List<ReportDTO> reports = reportService.getReportForAllSeasons();

        assertEquals(2, reports.size());
        reports.forEach(System.out::println);


    }

}
