package com.nagarro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	//find product by code
	Product findByCode(String code);
	
	//Query to search a product
	 @Query("SELECT p FROM Product p WHERE " +
	            "(:code IS NULL OR p.code = :code) " +
	            "AND (:brand IS NULL OR :brand = '' OR p.brand = :brand) " +
	            "AND (:name IS NULL OR :brand = '' OR p.name = :name)")
	    List<Product> searchProducts(
	            String code, String brand, String name);
	 

}
