package com.company.inventorySystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.inventorySystem.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
	
	//En este metodo se especifica la consulta personalizada
	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByName(String name);
	
	
	//metodo de consulta propio de spring.io
	List<Product> findByNameContainingIgnoreCase(String name);
	
	

}
