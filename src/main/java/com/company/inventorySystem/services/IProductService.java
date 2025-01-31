package com.company.inventorySystem.services;

import org.springframework.http.ResponseEntity;

import com.company.inventorySystem.model.Product;
import com.company.inventorySystem.response.ProductResponseRest;

public interface IProductService {
	
	public ResponseEntity<ProductResponseRest> saveProduct(Product product, Long categoryId); 
	

}
