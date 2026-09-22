package com.agriflow.agriflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}