package com.agriflow.agriflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.Crop;
import com.agriflow.agriflow.service.CropService;

import jakarta.validation.Valid;

@RestController
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping("/crops")
    public Crop saveCrop(@Valid @RequestBody Crop crop) {
        return cropService.saveCrop(crop);
    }

    @GetMapping("/crops")
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/crops/{id}")
    public Crop getCropById(@PathVariable Long id) {
        return cropService.getCropById(id);
    }

    @PutMapping("/crops/{id}")
    public Crop updateCrop(
            @PathVariable Long id,
            @Valid @RequestBody Crop crop) {

        return cropService.updateCrop(id, crop);
    }

    @DeleteMapping("/crops/{id}")
    public String deleteCrop(@PathVariable Long id) {

        cropService.deleteCrop(id);

        return "Crop deleted successfully";
    }
}