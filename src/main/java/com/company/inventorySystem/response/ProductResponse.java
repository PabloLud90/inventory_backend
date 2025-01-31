package com.company.inventorySystem.response;

import java.util.List;

import com.company.inventorySystem.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	
	List<Product> products;

}
