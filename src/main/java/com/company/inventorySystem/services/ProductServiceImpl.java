package com.company.inventorySystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventorySystem.dao.ICategoryDao;
import com.company.inventorySystem.dao.IProductDao;
import com.company.inventorySystem.model.Category;
import com.company.inventorySystem.model.Product;

import com.company.inventorySystem.response.ProductResponseRest;

@Service
public class ProductServiceImpl implements IProductService {

	private IProductDao productDao;
	private ICategoryDao categoryDao;
	
	public ProductServiceImpl(IProductDao productDao, ICategoryDao categoryDao) {
		super();
		this.productDao = productDao;
		this.categoryDao = categoryDao;
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> saveProduct(Product product, Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		
		try {
			//Buscar la categoria para setearla al objeto producto
			Optional<Category> category = categoryDao.findById(categoryId);
			
			if(category.isPresent()) {
				product.setCategory(category.get());
				
			}else {
				
				response.setMetadata("Respuesta no Ok", "-1", "Categoria no encontrada");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//Guardar el producto
			Product productSave = productDao.save(product);
			
			if(productSave != null) {
				list.add(productSave);
				response.getProductResponse().setProducts(list);
				response.setMetadata("Respuesta Ok", "00", "Producto guardado con exito");
				
			}else {
				response.setMetadata("Respuesta no Ok", "-1", "Producto no guardado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta no Ok", "-1", "Error al guardar el producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
			
		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
		
	}

}
