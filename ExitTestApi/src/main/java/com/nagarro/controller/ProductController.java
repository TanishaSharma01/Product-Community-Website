package com.nagarro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.exception.ApiRequestException;
import com.nagarro.model.ResponseJson;
import com.nagarro.service.ProductService;
import com.nagarro.utils.Constants;
import com.nagarro.utils.Rating;
import com.nagarro.utils.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = Constants.CLIENT_URL, allowCredentials = Constants.TRUE)
@RequestMapping(Constants.API_V1)
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	StringUtils stringUtils;

	//to get all products
	@GetMapping("/products")
	public ResponseEntity<Object> getAllProducts() {

		try {
			List<Product> products = this.productService.getAllProduct();

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson("All products fetched",products));
		}
		catch(Exception e) {
			throw new ApiRequestException("Products cannot be fetched.");
		}
	}
	
	//Search product
	@GetMapping("/products/search")
	public ResponseEntity<Object> searchProducts(HttpServletRequest request) {
		
		try {
			
		//First get query string from the url, it would be of form "search?code=LIV9922&name=Pants&brand=Levis"
		String q = request.getRequestURL().toString() + "?" + request.getQueryString();
		
		//Now parse query string
		Map<String, String> queryMap = stringUtils.parseQueryString(q);
		
		String productCode = queryMap.get("code"); //get product code
		String productName = queryMap.get("name"); //get product name
		String productBrand = queryMap.get("brand"); //get product brand
 
		//search product corresponding to the request product code, brand and name
		List<Product> arr = this.productService.searchProducts(productCode, productBrand, productName);

		for (Product prod : arr) {
			int averageRating = Rating.computeWeightedAverageRating(prod.getReviews());// compute product average rating
			prod.setRating(averageRating); // set product average rating
		}

		ResponseJson data = new ResponseJson(arr);
		return ResponseEntity.ok().body(data);
		}
		catch(Exception e) {
			throw new ApiRequestException("Products could not be searched.");
		}
	}

	//Search product by code
	@GetMapping("/products/{code}")
	public ResponseEntity<Object> getProduct(HttpServletRequest request,
			@PathVariable(name = "code") String productCode) {
		try {
		Product product = this.productService.getProductByCode(productCode);
		if(product==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson("Product for this code doesn't exist."));
		}
		int averageRating = Rating.computeWeightedAverageRating(product.getReviews());// compute average rating
		product.setRating(averageRating);
		return ResponseEntity.ok().body(new ResponseJson("Product Searched successfully",product));
		}
		catch(Exception e) {
			throw new ApiRequestException("Product could not be searched.");
		}
	}

	//Add products
	@PostMapping("/products/add")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		try {
			
		//if product code, name or brand is null then product would not be added
		if(product.getCode()==null || product.getName()==null || product.getBrand()==null || 
				product.getCode()=="" || product.getName()=="" || product.getBrand()=="") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson("Product not added because a parameter left empty."));
		}
		
		//check if product for the product we're trying to add already exists or not
		Boolean productExists = this.productService.checkProductExists(product.getCode());
		ResponseJson responseObj = new ResponseJson();
		
		//If product already exists then product would not get added 
		if (productExists) {
			responseObj.setMessage("Product already exists");
			return ResponseEntity.status(HttpStatus.FOUND).body(responseObj);
		}
		
		//Save product
		Product savedProduct = this.productService.saveProduct(product);
		responseObj.setData(savedProduct);
		responseObj.setMessage("Product added successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(responseObj);
		}
		catch(Exception e) {
			throw new ApiRequestException("Products could not be added.");
		}
	}
	
	//to count number of products
	@GetMapping("/products/count-products")
	public Long countProduct() {
		try {
		Long countProducts= this.productService.countProducts();
		return countProducts;
		}
		catch(Exception e) {
			throw new ApiRequestException("Products count could not be fetched.");
		}
	}
}
