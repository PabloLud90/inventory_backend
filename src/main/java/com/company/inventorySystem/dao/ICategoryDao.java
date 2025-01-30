package com.company.inventorySystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventorySystem.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{
	

}
