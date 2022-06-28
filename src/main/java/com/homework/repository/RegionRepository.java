package com.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homework.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
	Region findByCode(int code);
}
