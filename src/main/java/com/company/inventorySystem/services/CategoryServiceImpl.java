package com.company.inventorySystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventorySystem.dao.ICategoryDao;
import com.company.inventorySystem.model.Category;
import com.company.inventorySystem.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();

			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta Ok", "00", "Respuesta exitosa");

		} catch (Exception e) {

			response.setMetadata("Respuesta no Ok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		List<Category> list = new ArrayList<>();
		try {
	
			Optional<Category> category = categoryDao.findById(id);	
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta Ok", "00", "Categoria encontrada");
			}else {
				response.setMetadata("Respuesta no Ok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			

		} catch (Exception e) {

			response.setMetadata("Respuesta no Ok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> saveCategory(Category category) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			Category categorySave = categoryDao.save(category);
			
			if(categorySave != null) {
				list.add(categorySave);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta Ok", "00", "Categoria guardada");
			}else {
				response.setMetadata("Respuesta no Ok", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				
			}
	
		} catch (Exception e) {

			response.setMetadata("Respuesta no Ok", "-1", "Error al guardar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			Optional<Category> categorySearch = categoryDao.findById(id);
			
			if(categorySearch.isPresent()) {
				//Se procede a actualizar
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				
				Category categoryToUpdate = categoryDao.save(categorySearch.get());
				
				if(categoryToUpdate != null) {
					list.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("Respuesta ok", "00", "Se actualizo la categoria");
					
				}else {
					response.setMetadata("Respuesta no Ok", "-1", "Categoria no se actualizo");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);					
				}
				
				
			}else {
				response.setMetadata("Respuesta no Ok", "-1", "Categoria no se actualizo");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
				
				
			}
	
		} catch (Exception e) {

			response.setMetadata("Respuesta no Ok", "-1", "Error al actualizar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<CategoryResponseRest> deleteCategory(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			categoryDao.deleteById(id);
			response.setMetadata("Respuesta Ok", "00", "Categoria eliminada correctamente");
			

		} catch (Exception e) {

			response.setMetadata("Respuesta no Ok", "-1", "Error al eliminar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
		
	}
}
