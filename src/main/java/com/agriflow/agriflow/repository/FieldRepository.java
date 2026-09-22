package com.agriflow.agriflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {

}