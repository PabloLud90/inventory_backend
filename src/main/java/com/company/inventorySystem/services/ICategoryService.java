package com.company.inventorySystem.services;

import org.springframework.http.ResponseEntity;

import com.company.inventorySystem.model.Category;
import com.company.inventorySystem.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	
	public ResponseEntity<CategoryResponseRest> saveCategory(Category category);
	
	public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id);
	
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);

}
