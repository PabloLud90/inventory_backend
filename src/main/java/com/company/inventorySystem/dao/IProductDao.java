package com.company.inventorySystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventorySystem.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
	
	

}
