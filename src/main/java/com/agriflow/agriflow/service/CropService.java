package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.Crop;
import com.agriflow.agriflow.repository.CropRepository;

@Service
public class CropService {

    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Crop getCropById(Long id) {
        return cropRepository.findById(id).orElse(null);
    }

    public Crop updateCrop(Long id, Crop crop) {

        Crop existingCrop = cropRepository.findById(id).orElse(null);

        if (existingCrop != null) {

            existingCrop.setCropName(crop.getCropName());
//            existingCrop.setScientificName(crop.getScientificName());
//            existingCrop.setSuitableSeasons(crop.getSuitableSeasons());
//            existingCrop.setSuitableSoil(crop.getSuitableSoil());
//            existingCrop.setGrowthDuration(crop.getGrowthDuration());
//            existingCrop.setFarmingTechniques(crop.getFarmingTechniques());
//            existingCrop.setCommonPests(crop.getCommonPests());
//            existingCrop.setCommonDiseases(crop.getCommonDiseases());
//            existingCrop.setPrecautions(crop.getPrecautions());

            return cropRepository.save(existingCrop);
        }

        return null;
    }

    public void deleteCrop(Long id) {
        cropRepository.deleteById(id);
    }
}