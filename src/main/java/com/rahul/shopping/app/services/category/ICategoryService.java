package com.rahul.shopping.app.services.category;

import java.util.List;

import com.rahul.shopping.app.model.Category;
import com.rahul.shopping.app.request.CategoryRequest;

public interface ICategoryService {
	
	Category getCategoryById(Long id);
	
	Category getCategoryByName(String name);
	
	List<Category> getAllAcategories();
	
	Category addCategory(CategoryRequest categoryRequest);
	
	void updateCategory(CategoryRequest categoryRequest, Long id);
	
	void deleteCategory(Long id);
	
	
	

}
