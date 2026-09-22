package com.agriflow.agriflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.WaterRequirement;
import com.agriflow.agriflow.service.WaterRequirementService;

@RestController
public class WaterRequirementController {

    private final WaterRequirementService waterRequirementService;

    public WaterRequirementController(WaterRequirementService waterRequirementService) {
        this.waterRequirementService = waterRequirementService;
    }

    @PostMapping("/water-requirements")
    public WaterRequirement saveWaterRequirement(
            @RequestBody WaterRequirement waterRequirement) {

        return waterRequirementService.saveWaterRequirement(waterRequirement);
    }

    @GetMapping("/water-requirements")
    public List<WaterRequirement> getAllWaterRequirements() {
        return waterRequirementService.getAllWaterRequirements();
    }

    @GetMapping("/water-requirements/{id}")
    public WaterRequirement getWaterRequirementById(@PathVariable Long id) {
        return waterRequirementService.getWaterRequirementById(id);
    }

    @PutMapping("/water-requirements/{id}")
    public WaterRequirement updateWaterRequirement(
            @PathVariable Long id,
            @RequestBody WaterRequirement waterRequirement) {

        return waterRequirementService.updateWaterRequirement(
                id, waterRequirement);
    }

    @DeleteMapping("/water-requirements/{id}")
    public String deleteWaterRequirement(@PathVariable Long id) {
        waterRequirementService.deleteWaterRequirement(id);
        return "Water requirement deleted successfully";
    }
}