package com.homework;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.homework.model.Product;
import com.homework.model.Region;
import com.homework.object.EliObject;
import com.homework.repository.ProductRepository;
import com.homework.repository.RegionRepository;
import com.homework.util.JSON;

@Configuration
public class DataInitializer {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	private List<Product> products = new ArrayList<Product>();
	private List<Region> regions = new ArrayList<Region>(); 
	
	@Bean
	public void init() {
		try (FileInputStream stream = new FileInputStream(new ClassPathResource("static/json/products.json").getFile())) {
			if (stream != null) for (Object object : JSON.streamToList(stream)) { products.add(new Product(new EliObject(object))); }
		} catch (Throwable e) { e.printStackTrace(System.err); } finally { if (products.size() > 0) productRepository.saveAll(products); }
		
		try (FileInputStream stream = new FileInputStream(new ClassPathResource("static/json/regions.json").getFile())) {
			if (stream != null) for (Object object : JSON.streamToList(stream)) { regions.add(new Region(new EliObject(object))); }
		} catch (Throwable e) { e.printStackTrace(System.err); } finally { if (regions.size() > 0) regionRepository.saveAll(regions); }
	}
}
