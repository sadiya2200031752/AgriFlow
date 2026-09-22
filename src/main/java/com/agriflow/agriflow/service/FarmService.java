package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.Farm;
import com.agriflow.agriflow.repository.FarmRepository;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public Farm saveFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id).orElse(null);
    }

    public Farm updateFarm(Long id, Farm farm) {

        Farm existingFarm = farmRepository.findById(id).orElse(null);

        if (existingFarm != null) {
            existingFarm.setFarmName(farm.getFarmName());
            existingFarm.setArea(farm.getArea());
            existingFarm.setLocation(farm.getLocation());
            existingFarm.setFarmer(farm.getFarmer());

            return farmRepository.save(existingFarm);
        }

        return null;
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}