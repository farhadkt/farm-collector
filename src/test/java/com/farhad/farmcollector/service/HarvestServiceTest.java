package com.farhad.farmcollector.service;

import com.farhad.farmcollector.data.entity.Harvest;
import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.data.repository.HarvestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HarvestServiceTest {

    @InjectMocks
    private HarvestService harvestService;

    @Mock
    private HarvestRepository harvestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllHarvests() {
        Harvest harvest = new Harvest();
        harvest.setId(1L);
        harvest.setActualHarvestAmount(50.0);
        harvest.setPlant(new Plant());

        when(harvestRepository.findAll()).thenReturn(Arrays.asList(harvest));

        var harvests = harvestService.getAllHarvests();

        assertEquals(1, harvests.size());
        assertEquals(50.0, harvests.get(0).getActualHarvestAmount());
    }

    @Test
    void saveHarvest() {
        Harvest harvest = new Harvest();
        harvest.setActualHarvestAmount(50.0);
        harvest.setPlant(new Plant());

        when(harvestRepository.save(harvest)).thenReturn(harvest);

        var result = harvestService.saveHarvest(harvest);

        assertEquals(50.0, result.getActualHarvestAmount());
    }

}
