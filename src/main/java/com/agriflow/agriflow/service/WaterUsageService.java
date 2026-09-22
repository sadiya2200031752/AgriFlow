package com.agriflow.agriflow.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.WaterUsage;
import com.agriflow.agriflow.repository.WaterUsageRepository;

@Service
public class WaterUsageService {

    private final WaterUsageRepository waterUsageRepository;

    public WaterUsageService(WaterUsageRepository waterUsageRepository) {
        this.waterUsageRepository = waterUsageRepository;
    }

    public WaterUsage saveWaterUsage(WaterUsage waterUsage) {

        waterUsage.setUsageDate(LocalDate.now());

        return waterUsageRepository.save(waterUsage);
    }

    public List<WaterUsage> getAllWaterUsages() {
        return waterUsageRepository.findAll();
    }

    public WaterUsage getWaterUsageById(Long id) {
        return waterUsageRepository.findById(id).orElse(null);
    }

    public WaterUsage updateWaterUsage(Long id, WaterUsage waterUsage) {

        WaterUsage existingWaterUsage =
                waterUsageRepository.findById(id).orElse(null);

        if (existingWaterUsage != null) {
            existingWaterUsage.setAmountUsed(waterUsage.getAmountUsed());
            existingWaterUsage.setField(waterUsage.getField());

            return waterUsageRepository.save(existingWaterUsage);
        }

        return null;
    }

    public void deleteWaterUsage(Long id) {
        waterUsageRepository.deleteById(id);
    }
}