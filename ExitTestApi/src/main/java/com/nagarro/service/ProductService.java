package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.ProductDao;
import com.nagarro.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	//get Product by code
	public Product getProductByCode(String code) {
		return this.productDao.findByCode(code);
		
	}
	
	//get all products
	public List<Product> getAllProduct() {
		return this.productDao.findAll();
	}

	//save product
	public Product saveProduct(Product product) {
		return this.productDao.save(product);
	}

	//delete product
	public void deleteProductByCode(String code) {
		Product toDeleteProduct = this.getProductByCode(code);
		if(toDeleteProduct != null) {
			this.productDao.delete(toDeleteProduct);
		}
 	}
	
	//update product
	public Product updateProduct(Product product) {
		return this.productDao.save(product);
	}
	
	//count products
	public Long countProducts() {
		return this.productDao.count();
	}
	
	//search product
	public List<Product> searchProducts(String code, String name, String brand) {
		return this.productDao.searchProducts(code, name, brand);
	}

	//check if product exists
	public Boolean checkProductExists(String code) {
		return (this.getProductByCode(code)!=null ? true : false);
	}
	
}

