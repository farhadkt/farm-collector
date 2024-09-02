package com.farhad.farmcollector.data.repository;

import com.farhad.farmcollector.data.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findAllBySeason(String season);
}
