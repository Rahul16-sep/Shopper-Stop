package com.rahul.shopping.app.services.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rahul.shopping.app.dto.ImageDTO;
import com.rahul.shopping.app.model.Image;

public interface IImageService {
	
	Image getImageById(Long id);
	
	void deleteImageById(Long id);
	
	List<ImageDTO> saveImages(List<MultipartFile> fFile, Long productId);
	
	void updateImage(MultipartFile multiPartFile, Long imageId);
	
	List<ImageDTO> findByProductId(Long productId);

}
