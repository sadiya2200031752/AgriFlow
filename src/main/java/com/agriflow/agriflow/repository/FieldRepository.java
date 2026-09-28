package com.agriflow.agriflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agriflow.agriflow.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query("SELECT COALESCE(SUM(f.area), 0) FROM Field f WHERE f.farm.id = :farmId")
    double getTotalAreaByFarmId(@Param("farmId") Long farmId);
}