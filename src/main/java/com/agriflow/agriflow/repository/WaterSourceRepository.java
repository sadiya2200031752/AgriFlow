package com.agriflow.agriflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.WaterSource;

public interface WaterSourceRepository extends JpaRepository<WaterSource, Long> {

    List<WaterSource> findByFarmId(Long farmId);

}