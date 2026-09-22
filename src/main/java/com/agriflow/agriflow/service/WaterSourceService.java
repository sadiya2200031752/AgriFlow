package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.WaterSource;
import com.agriflow.agriflow.repository.WaterSourceRepository;

@Service
public class WaterSourceService {

    private final WaterSourceRepository waterSourceRepository;

    public WaterSourceService(WaterSourceRepository waterSourceRepository) {
        this.waterSourceRepository = waterSourceRepository;
    }

    public WaterSource saveWaterSource(WaterSource waterSource) {
        return waterSourceRepository.save(waterSource);
    }

    public List<WaterSource> getAllWaterSources() {
        return waterSourceRepository.findAll();
    }

    public WaterSource getWaterSourceById(Long id) {
        return waterSourceRepository.findById(id).orElse(null);
    }

    public WaterSource updateWaterSource(Long id, WaterSource waterSource) {

        WaterSource existingWaterSource =
                waterSourceRepository.findById(id).orElse(null);

        if (existingWaterSource != null) {
            existingWaterSource.setSourceName(waterSource.getSourceName());
            existingWaterSource.setCapacity(waterSource.getCapacity());
            existingWaterSource.setFarm(waterSource.getFarm());

            return waterSourceRepository.save(existingWaterSource);
        }

        return null;
    }

    public void deleteWaterSource(Long id) {
        waterSourceRepository.deleteById(id);
    }
}