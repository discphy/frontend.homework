package com.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.homework.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContaining(@Param("name") String name);
	Product findByCodeOrImageUrl(int code, String imageUrl);
}
