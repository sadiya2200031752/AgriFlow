package com.agriflow.agriflow.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.agriflow.agriflow.model.Farmer;
import com.agriflow.agriflow.service.FarmerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/farmers")
    public Farmer saveFarmer(@RequestBody Farmer farmer) {
        return farmerService.saveFarmer(farmer);
    }
    @GetMapping("/farmers")
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }
    @GetMapping("/farmers/{id}")
    public Farmer getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }
    @PutMapping("/farmers/{id}")
    public Farmer updateFarmer(@PathVariable Long id, @RequestBody Farmer farmer) {
        return farmerService.updateFarmer(id, farmer);
    }
    @DeleteMapping("/farmers/{id}")
    public String deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return "Farmer deleted successfully";
    }
    
}