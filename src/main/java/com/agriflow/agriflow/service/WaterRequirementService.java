package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.WaterRequirement;
import com.agriflow.agriflow.repository.WaterRequirementRepository;

@Service
public class WaterRequirementService {

    private final WaterRequirementRepository waterRequirementRepository;

    public WaterRequirementService(WaterRequirementRepository waterRequirementRepository) {
        this.waterRequirementRepository = waterRequirementRepository;
    }

    public WaterRequirement saveWaterRequirement(WaterRequirement waterRequirement) {
        return waterRequirementRepository.save(waterRequirement);
    }

    public List<WaterRequirement> getAllWaterRequirements() {
        return waterRequirementRepository.findAll();
    }

    public WaterRequirement getWaterRequirementById(Long id) {
        return waterRequirementRepository.findById(id).orElse(null);
    }

    public WaterRequirement updateWaterRequirement(Long id, WaterRequirement waterRequirement) {

        WaterRequirement existingWaterRequirement =
                waterRequirementRepository.findById(id).orElse(null);

        if (existingWaterRequirement != null) {
            existingWaterRequirement.setRequiredAmount(
                    waterRequirement.getRequiredAmount());

            existingWaterRequirement.setField(
                    waterRequirement.getField());

            return waterRequirementRepository.save(existingWaterRequirement);
        }

        return null;
    }

    public void deleteWaterRequirement(Long id) {
        waterRequirementRepository.deleteById(id);
    }
}