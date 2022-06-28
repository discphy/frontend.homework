package com.homework.service;

import org.springframework.stereotype.Service;

import com.homework.model.Region;
import com.homework.repository.RegionRepository;

@Service
public class RegionService {
	private final RegionRepository regionRepository;

	public RegionService(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}

	public Region searchOne(int code) {
		return regionRepository.findByCode(code); 
	}
}
