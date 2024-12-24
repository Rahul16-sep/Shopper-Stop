package com.rahul.shopping.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.shopping.app.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
	List<Image> findImageByProductId(Long productId);

}
