package com.agriflow.agriflow.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agriflow.agriflow.model.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
	List<Farm> findByFarmerId(Long farmerId);
}