package com.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homework.model.Product;
import com.homework.model.Region;
import com.homework.service.ProductService;
import com.homework.service.RegionService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private final ProductService productService;
	
	private final RegionService regionService;

	public ProductController(ProductService productService, RegionService regionService) {
		this.productService = productService;
		this.regionService = regionService;
	}

	@GetMapping("/search")
	public String search() {
		return "product_search";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam("query") String query, Model model) {
		Product product;
		Region region;
		
		if (query.strip().equals("")) return "product_list"; 
		
		try {
			if ((product = productService.searchOne(query)) != null) { 
				model.addAttribute("product", product);
				
				if ((region = regionService.searchOne(product.getCode())) != null) {
					model.addAttribute("region", region);
				}
			}
			
			model.addAttribute("list", productService.searchList(query));
		} catch (Throwable e) { e.printStackTrace(System.err); }
		
		return "product_list";
	}
}
