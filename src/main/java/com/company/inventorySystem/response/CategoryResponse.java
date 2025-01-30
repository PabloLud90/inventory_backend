package com.company.inventorySystem.response;

import java.util.List;

import com.company.inventorySystem.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;

}
