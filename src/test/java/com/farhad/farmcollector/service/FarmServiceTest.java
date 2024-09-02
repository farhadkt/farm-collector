package com.farhad.farmcollector.service;


import com.farhad.farmcollector.data.entity.Farm;
import com.farhad.farmcollector.data.repository.FarmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FarmServiceTest {

    @InjectMocks
    private FarmService farmService;

    @Mock
    private FarmRepository farmRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFarms() {
        Farm farm = new Farm(1L, "FarmA");
        farm.setId(1L);
        farm.setName("Farm A");

        when(farmRepository.findAll()).thenReturn(Arrays.asList(farm));

        assertEquals(1, farmService.getAllFarms().size());
        assertEquals("Farm A", farmService.getAllFarms().get(0).getName());
    }

    @Test
    void saveFarm() {
        Farm farm = new Farm();
        farm.setName("Farm A");

        when(farmRepository.save(farm)).thenReturn(farm);

        assertEquals("Farm A", farmService.saveFarm(farm).getName());
    }



}
