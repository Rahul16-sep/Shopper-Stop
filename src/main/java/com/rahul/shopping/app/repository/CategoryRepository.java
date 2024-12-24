package com.rahul.shopping.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.shopping.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(String name);
	
	//Optional<Category> findById(Long id);

}
