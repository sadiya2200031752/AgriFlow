package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.exception.FieldNotFoundException;
import com.agriflow.agriflow.model.Field;
import com.agriflow.agriflow.model.WaterAnalysis;
import com.agriflow.agriflow.model.WaterRequirement;
import com.agriflow.agriflow.model.WaterSource;
import com.agriflow.agriflow.model.WaterUsage;
import com.agriflow.agriflow.repository.FieldRepository;
import com.agriflow.agriflow.repository.WaterRequirementRepository;
import com.agriflow.agriflow.repository.WaterSourceRepository;
import com.agriflow.agriflow.repository.WaterUsageRepository;

@Service
public class WaterAnalysisService {

    private final FieldRepository fieldRepository;
    private final WaterSourceRepository waterSourceRepository;
    private final WaterUsageRepository waterUsageRepository;
    private final WaterRequirementRepository waterRequirementRepository;

    public WaterAnalysisService(
            FieldRepository fieldRepository,
            WaterSourceRepository waterSourceRepository,
            WaterUsageRepository waterUsageRepository,
            WaterRequirementRepository waterRequirementRepository) {

        this.fieldRepository = fieldRepository;
        this.waterSourceRepository = waterSourceRepository;
        this.waterUsageRepository = waterUsageRepository;
        this.waterRequirementRepository = waterRequirementRepository;
    }

    public WaterAnalysis analyzeField(Long fieldId) {

        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() ->
                        new FieldNotFoundException(
                                "Field not found with ID: " + fieldId));

        if (field.getFarm() == null) {
            throw new RuntimeException("Farm not assigned to this field");
        }

        Long farmId = field.getFarm().getId();

        List<WaterSource> sources =
                waterSourceRepository.findByFarmId(farmId);

        List<WaterUsage> usages =
                waterUsageRepository.findByFieldId(fieldId);

        List<WaterRequirement> requirements =
                waterRequirementRepository.findByFieldId(fieldId);

        double totalCapacity = 0;

        for (WaterSource source : sources) {
            totalCapacity += source.getCapacity();
        }

        double totalUsed = 0;

        for (WaterUsage usage : usages) {
            totalUsed += usage.getAmountUsed();
        }

        double requiredWater = 0;

        if (!requirements.isEmpty()) {
            requiredWater = requirements.get(0).getRequiredAmount();
        }

        double remainingWater = totalCapacity - totalUsed;

        WaterAnalysis analysis = new WaterAnalysis();

        double waterBalance = remainingWater - requiredWater;

        analysis.setAvailableWater(remainingWater);
        analysis.setRequiredWater(requiredWater);
        analysis.setUsedWater(totalUsed);
        analysis.setWaterBalance(waterBalance);

        if (remainingWater >= requiredWater) {
            analysis.setStatus("ENOUGH");
            analysis.setRecommendation(
                    "Water is sufficient for the current requirement.");
        } else {
            analysis.setStatus("DEFICIT");

            double additionalWater = requiredWater - remainingWater;

            analysis.setRecommendation(
                    "Water deficit detected. Additional "
                    + additionalWater
                    + " L is required.");
        }

        return analysis;
    }
}