package com.agriflow.agriflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.WaterUsage;

public interface WaterUsageRepository extends JpaRepository<WaterUsage, Long> {

    List<WaterUsage> findByFieldId(Long fieldId);

}