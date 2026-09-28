package com.agriflow.agriflow.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.Crop;
public interface CropRepository extends JpaRepository<Crop,Long> {

}
