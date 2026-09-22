package com.agriflow.agriflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.WaterSource;
import com.agriflow.agriflow.service.WaterSourceService;

@RestController
public class WaterSourceController {

    private final WaterSourceService waterSourceService;

    public WaterSourceController(WaterSourceService waterSourceService) {
        this.waterSourceService = waterSourceService;
    }

    @PostMapping("/water-sources")
    public WaterSource saveWaterSource(@RequestBody WaterSource waterSource) {
        return waterSourceService.saveWaterSource(waterSource);
    }

    @GetMapping("/water-sources")
    public List<WaterSource> getAllWaterSources() {
        return waterSourceService.getAllWaterSources();
    }

    @GetMapping("/water-sources/{id}")
    public WaterSource getWaterSourceById(@PathVariable Long id) {
        return waterSourceService.getWaterSourceById(id);
    }

    @PutMapping("/water-sources/{id}")
    public WaterSource updateWaterSource(
            @PathVariable Long id,
            @RequestBody WaterSource waterSource) {

        return waterSourceService.updateWaterSource(id, waterSource);
    }

    @DeleteMapping("/water-sources/{id}")
    public String deleteWaterSource(@PathVariable Long id) {
        waterSourceService.deleteWaterSource(id);
        return "Water source deleted successfully";
    }
}