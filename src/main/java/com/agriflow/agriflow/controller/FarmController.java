package com.agriflow.agriflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.Farm;
import com.agriflow.agriflow.service.FarmService;

@RestController
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping("/farms")
    public Farm saveFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    @GetMapping("/farms")
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/farms/{id}")
    public Farm getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @PutMapping("/farms/{id}")
    public Farm updateFarm(@PathVariable Long id, @RequestBody Farm farm) {
        return farmService.updateFarm(id, farm);
    }

    @DeleteMapping("/farms/{id}")
    public String deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return "Farm deleted successfully";
    }
}