package com.company.inventorySystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventorySystem.model.Category;
import com.company.inventorySystem.response.CategoryResponseRest;
import com.company.inventorySystem.services.ICategoryService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	/**
	 * obtner todas las categorias
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories(){
		
		ResponseEntity<CategoryResponseRest> response = service.search();		
		return response;
		
	}
	
	/**
	 * Obtener categorias por id
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);		
		return response;
		
	}
	
	/**
	 * Guardar categorias
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category){
		ResponseEntity<CategoryResponseRest> response = service.saveCategory(category);		
		return response;
		
	}
	
	/**
	 * Actualizar categoria
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category,@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.updateCategory(category, id);
		return response;
		
	}
	
	/**
	 * Eliminar cateegoria
	 * @param id
	 * @return
	 */
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> deleteCategory(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> respose = service.deleteCategory(id);
		return respose;
	}
	
 
}
