package com.agriflow.agriflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.WaterRequirement;

public interface WaterRequirementRepository extends JpaRepository<WaterRequirement, Long> {

    List<WaterRequirement> findByFieldId(Long fieldId);

}