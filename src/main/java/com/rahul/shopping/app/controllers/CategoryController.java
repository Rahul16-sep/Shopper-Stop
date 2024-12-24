package com.rahul.shopping.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.shopping.app.exception.CategoryAllReadyExistException;
import com.rahul.shopping.app.exception.CategoryNotFoundException;
import com.rahul.shopping.app.model.Category;
import com.rahul.shopping.app.request.CategoryRequest;
import com.rahul.shopping.app.response.APIResponse;
import com.rahul.shopping.app.services.category.ICategoryService;


@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
	
	@Autowired
	private final ICategoryService categoryService;
	
	public CategoryController(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<APIResponse> getAllcategories() {
		
		try {
			List<Category> allCategories = categoryService.getAllAcategories();
			return ResponseEntity.ok().body(new APIResponse("Found All Categories", allCategories));
		}catch(Exception e) {
			return  ResponseEntity.internalServerError().body(new APIResponse("INTERNAL SERVER ERROR", "category not found"));
		}
		
	}
	
	
	@PostMapping("/add/category")
	public ResponseEntity<APIResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
		try {
			Category category = categoryService.addCategory(categoryRequest);
			return ResponseEntity.ok(new APIResponse("Category Added Successfully", category));
		}catch(CategoryAllReadyExistException e) {
			return ResponseEntity.internalServerError().body(new APIResponse("INTERNAL SERVER ERROR", "CATEGORY ALL READY EXIST"));
		}
		
	}
	
	
	@GetMapping("/get/{name}/category")
	public ResponseEntity<APIResponse> getCategoryByName(@PathVariable String name){
		try {
			Category category = categoryService.getCategoryByName(name);
			return ResponseEntity.ok(new APIResponse("Found Category", category));
		}catch(Exception e) {
			return  ResponseEntity.internalServerError().body(new APIResponse("INTERNAL SERVER ERROR", "category not found"));
		}
	}
	
	
	@GetMapping("/get/{id}/category")
	public ResponseEntity<APIResponse> getCategoryById(@PathVariable Long id){
		try {
			Category category = categoryService.getCategoryById(id);
			return ResponseEntity.ok(new APIResponse("Found Category", category));
		}catch(Exception e) {
			return  ResponseEntity.internalServerError().body(new APIResponse("INTERNAL SERVER ERROR", "category not found"));
		}
	}
	
	
	@PutMapping("/update/{id}/category")
	public ResponseEntity<APIResponse> updateCategory(@RequestBody CategoryRequest categoryrequest, Long id){
		try {
			categoryService.updateCategory(categoryrequest, id);
			return ResponseEntity.ok().body(new APIResponse("Ok", "Category Updated Successfully"));
		}catch(CategoryNotFoundException e) {
			return ResponseEntity.badRequest().body(new APIResponse("Internal Server Error", "Category Not Found"));
		}
	}
	
	
	@DeleteMapping("/category/{id}/delete")
	public ResponseEntity<APIResponse> deleteCategoryById(@PathVariable Long id){
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.ok().body(new APIResponse("Ok", "Category Deleted Successfully"));
		}catch(CategoryNotFoundException e) {
			return ResponseEntity.badRequest().body(new APIResponse("Internal Server Error", "Category Not Found"));
		}
	}

}
