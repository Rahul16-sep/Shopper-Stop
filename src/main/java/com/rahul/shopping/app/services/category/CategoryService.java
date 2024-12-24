package com.rahul.shopping.app.services.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.shopping.app.exception.CategoryAllReadyExistException;
import com.rahul.shopping.app.exception.CategoryNotFoundException;
import com.rahul.shopping.app.model.Category;
import com.rahul.shopping.app.repository.CategoryRepository;
import com.rahul.shopping.app.request.CategoryRequest;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
	
	@Autowired
	private  CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> 
				{throw new CategoryNotFoundException("Category Not Found for this id !");});
	}
	

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
	

	@Override
	public List<Category> getAllAcategories() {
		return categoryRepository.findAll();
	}
	

	@Override
	public Category addCategory(CategoryRequest categoryRequest) {
//		Category categoryByName = categoryRepository.findByName(categoryRequest.getName());
//		if(categoryByName != null) {
//			throw new CategoryAllReadyExistException("Category already present in database");
//		}else {
//			return categoryRepository.save(createCategory(categoryRequest));
//		}
		
		return Optional.of(categoryRequest).filter(c -> !categoryRequest.findByName(c.getName()))
		.map(category ->  createCategory(categoryRequest))
		.map(categoryRepository :: save)
		.orElseThrow(() -> {throw new CategoryAllReadyExistException("Category All ready Exist");});
		
	}
	
	
	private Category createCategory(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.getName());
		return category;
	}

	
	@Override
	public void updateCategory(CategoryRequest categoryRequest, Long id) {
			categoryRepository.findById(id)
			.map(existingCategory -> updateExistingCategory(existingCategory, categoryRequest))
			.map(categoryRepository:: save)
			.orElseThrow(() -> {throw new CategoryNotFoundException("Category is not Found");});
	}
	
	
	public Category updateExistingCategory(Category existingCategory, CategoryRequest categoryRequest) {
		existingCategory.setName(categoryRequest.getName());
		return existingCategory;
	}
	

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.findById(id)
			.ifPresentOrElse(categoryRepository :: delete, () -> {
				throw new CategoryNotFoundException("Category Not Found for this id");
			});

	}
	
	
}
