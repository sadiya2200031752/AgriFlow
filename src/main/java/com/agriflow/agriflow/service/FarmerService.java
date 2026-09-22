package com.agriflow.agriflow.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.Farmer;
import com.agriflow.agriflow.repository.FarmerRepository;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }
   
    public List<Farmer> getAllFarmers(){
    	return farmerRepository.findAll();
    }
    public Farmer getFarmerById(Long id) {
    	return farmerRepository.findById(id).orElse(null);
    }
    public Farmer updateFarmer(Long id, Farmer farmer) {

        Farmer existingFarmer = farmerRepository.findById(id).orElse(null);

        if (existingFarmer != null) {
            existingFarmer.setName(farmer.getName());
            existingFarmer.setPhone(farmer.getPhone());
            existingFarmer.setLocation(farmer.getLocation());

            return farmerRepository.save(existingFarmer);
        }

        return null;
    }
    
    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }
}