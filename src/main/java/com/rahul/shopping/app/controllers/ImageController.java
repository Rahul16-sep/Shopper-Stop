package com.rahul.shopping.app.controllers;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rahul.shopping.app.dto.ImageDTO;
import com.rahul.shopping.app.model.Image;
import com.rahul.shopping.app.response.APIResponse;
import com.rahul.shopping.app.services.image.IImageService;


@RestController
@RequestMapping("${api.prefix}/images/")
public class ImageController {
	
	public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }
	
	@Autowired
	private final IImageService imageService;
	
	@PostMapping("/upload")
	public ResponseEntity<APIResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId){
		
		try {
			
			List<ImageDTO> saveImages = imageService.saveImages(files, productId);
			return  ResponseEntity.ok(new APIResponse("Images Uploaded Successfully", saveImages));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Upload Failed!", e.getMessage()));
		}
		
	}
	
	
	@GetMapping("/image/download/{id}")
	public ResponseEntity<Resource> downloadImage(@PathVariable("id") Long imageId) {
		/*
		Image image = imageService.getImageById(imageId);
		
		ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int)image.getImage().length()));
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFiletype()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename =\"" + image.getFilename() + "\"").body(resource); 
		
		*/
		
		//Trying a optimized way of the above code
		
			try {
				Image image = imageService.getImageById(imageId);
				Blob blob = image.getImage();
				InputStream inputStream = blob.getBinaryStream();
				
				return  ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(image.getFiletype()))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename =\"" + image.getFilename() + "\"")
						.body(new InputStreamResource(inputStream)); 
			}catch(Exception e) {
				return ResponseEntity.status(500).body(null);
			}	
			
		}
	
	
	@PutMapping("")
	public ResponseEntity<APIResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file){
		
		try {
			Image image = imageService.getImageById(imageId);
			
			if(image != null) {
				imageService.updateImage(file, imageId);
				return ResponseEntity.ok(new APIResponse("Image Updated Successfully", null));
			}else {
				return ResponseEntity.badRequest().body(new APIResponse("Bad Request", "Image Not Found"));
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Update Failed", "INTERNAL SERVER ERROR"));
		}	
		
	}
	
	@DeleteMapping("/image/{id}/delete")
	public ResponseEntity<APIResponse> deleteImage(@PathVariable Long imageId){
		
		try {
			Image image = imageService.getImageById(imageId);
			
			if(image != null) {
				imageService.deleteImageById(imageId);
				return ResponseEntity.ok(new APIResponse("Image Deleted Successfully", null));
			}else {
				return ResponseEntity.badRequest().body(new APIResponse("Bad Request", "Image Not Found"));
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Delete Failed", "INTERNAL SERVER ERROR"));
		}	
		
	}

}
