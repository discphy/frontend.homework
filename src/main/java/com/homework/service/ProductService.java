package com.homework.service;

import com.homework.model.Product;
import com.homework.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product searchOne(String query) {
		return productRepository.findByCodeOrImageUrl(Integer.parseInt(query.replaceAll("\\D", "0")), query);
	}
	
	public List<Product> searchList(String query) {
		return productRepository.findByNameContaining(query); 
	}
}
