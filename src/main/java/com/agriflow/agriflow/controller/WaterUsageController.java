package com.agriflow.agriflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.WaterUsage;
import com.agriflow.agriflow.service.WaterUsageService;

@RestController
public class WaterUsageController {

    private final WaterUsageService waterUsageService;

    public WaterUsageController(WaterUsageService waterUsageService) {
        this.waterUsageService = waterUsageService;
    }

    @PostMapping("/water-usages")
    public WaterUsage saveWaterUsage(@RequestBody WaterUsage waterUsage) {
        return waterUsageService.saveWaterUsage(waterUsage);
    }

    @GetMapping("/water-usages")
    public List<WaterUsage> getAllWaterUsages() {
        return waterUsageService.getAllWaterUsages();
    }

    @GetMapping("/water-usages/{id}")
    public WaterUsage getWaterUsageById(@PathVariable Long id) {
        return waterUsageService.getWaterUsageById(id);
    }

    @PutMapping("/water-usages/{id}")
    public WaterUsage updateWaterUsage(
            @PathVariable Long id,
            @RequestBody WaterUsage waterUsage) {

        return waterUsageService.updateWaterUsage(id, waterUsage);
    }

    @DeleteMapping("/water-usages/{id}")
    public String deleteWaterUsage(@PathVariable Long id) {
        waterUsageService.deleteWaterUsage(id);
        return "Water usage deleted successfully";
    }
}