package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.data.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlantServiceTest {

    @InjectMocks
    private PlantService plantService;

    @Mock
    private PlantRepository plantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlants() {
        Plant plant = new Plant();
        plant.setId(1L);
        plant.setCropType("Corn");
        plant.setSeason("Spring");

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant));

        var plants = plantService.getAllPlants();

        assertEquals(1, plants.size());
        assertEquals("Corn", plants.get(0).getCropType());
        assertEquals("Spring", plants.get(0).getSeason());
    }

    @Test
    void savePlant() {
        Plant plant = new Plant();
        plant.setCropType("Corn");
        plant.setSeason("Spring");

        when(plantRepository.save(plant)).thenReturn(plant);

        var result = plantService.savePlant(plant);

        assertEquals("Corn", result.getCropType());
        assertEquals("Spring", result.getSeason());
    }

}
