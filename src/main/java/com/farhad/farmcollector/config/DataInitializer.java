package com.farhad.farmcollector.config;
import com.farhad.farmcollector.data.entity.Farm;
import com.farhad.farmcollector.data.entity.Harvest;
import com.farhad.farmcollector.data.entity.Plant;
import com.farhad.farmcollector.data.repository.FarmRepository;
import com.farhad.farmcollector.data.repository.HarvestRepository;
import com.farhad.farmcollector.data.repository.PlantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(FarmRepository farmRepository, PlantRepository plantRepository, HarvestRepository harvestRepository) {
        return args -> {
            // Create Farms
            Farm farmA = new Farm(1l, "FarmA");
            Farm farmB = new Farm(2l,"FarmB");
            farmRepository.save(farmA);
            farmRepository.save(farmB);

            // Create Plants
            Plant cornPlant = new Plant(1l, farmA, "Field1", "Winter", 10.0, "Corn", 100.0);
            Plant potatoesPlant = new Plant(2l, farmA, "Field2", "Winter", 15.0, "Potatoes", 150.0);
            Plant summerCornPlant = new Plant(3l, farmB, "Field3", "Summer", 12.0, "Corn", 120.0);
            plantRepository.save(cornPlant);
            plantRepository.save(potatoesPlant);
            plantRepository.save(summerCornPlant);

            // Create Harvests
            Harvest cornHarvest = new Harvest(1l, 80.0, cornPlant);
            Harvest potatoesHarvest = new Harvest(2l, 140.0, potatoesPlant);
            Harvest summerCornHarvest = new Harvest(3l, 110.0, summerCornPlant);
            harvestRepository.save(cornHarvest);
            harvestRepository.save(potatoesHarvest);
            harvestRepository.save(summerCornHarvest);
        };
    }
}
