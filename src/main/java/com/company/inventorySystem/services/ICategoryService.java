package com.company.inventorySystem.services;

import org.springframework.http.ResponseEntity;

import com.company.inventorySystem.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();

}
