package com.company.inventorySystem.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventorySystem.model.Product;

import com.company.inventorySystem.response.ProductResponseRest;
import com.company.inventorySystem.services.IProductService;
import com.company.inventorySystem.util.Util;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	
	private IProductService service;
	
	
	public ProductRestController(IProductService service) {
		super();
		this.service = service;
	}




	/**
	 * Guardar producto asociado a una categoria
	 * @param picture
	 * @param name
	 * @param price
	 * @param account
	 * @param categoryId
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> saveProduct(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("account") int account,
			@RequestParam("categoryId") Long categoryId) throws IOException
	{
		Product product = new Product();
		product.setName(name);
		product.setAccount(account);
		product.setPrice(price);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = service.saveProduct(product, categoryId);
		
		return response;
		
	}
	
	/**
	 * Search ById
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id) {
		 ResponseEntity<ProductResponseRest> response = service.searchByID(id);
		 return response;
	}
	
	
	/**
	 * Buscar por nombre 
	 * @param name
	 * @return
	 */
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name) {
		 ResponseEntity<ProductResponseRest> response = service.searchByName(name);	
	
		 return response;
	}
	
	/**
	 * Eliminar por id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> deletwById(@PathVariable Long id) {
		 ResponseEntity<ProductResponseRest> response = service.deleteProduct(id);
		 return response;
	}
	
	
	/**
	 * Buscar todos los productos
	 * @param name
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search() {
		 ResponseEntity<ProductResponseRest> response = service.search();	
	
		 return response;
	}
	
	

}
