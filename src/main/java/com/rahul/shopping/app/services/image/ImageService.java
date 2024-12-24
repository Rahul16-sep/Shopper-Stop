package com.rahul.shopping.app.services.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rahul.shopping.app.dto.ImageDTO;
import com.rahul.shopping.app.exception.ResourceNotFoundException;
import com.rahul.shopping.app.model.Image;
import com.rahul.shopping.app.model.Product;
import com.rahul.shopping.app.repository.ImageRepository;
import com.rahul.shopping.app.repository.ProductRepository;

@Service
public class ImageService implements IImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Image getImageById(Long id) {
		return imageRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Image is not Found");
		});
	}

	@Override
	public void deleteImageById(Long id) {
		imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
			throw new ResourceNotFoundException("Image is not Found");
		});

	}

	@Override
	public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) throws RuntimeException {
		Product product = productRepository.findById(productId).orElseThrow(() -> {
			throw new ResourceNotFoundException("Product Not Found");
		});

		List<ImageDTO> savedImageDto = new ArrayList<>();

		files.stream().forEach(file -> {
			Image image = new Image();
			image.setFilename(file.getOriginalFilename());
			image.setFiletype(file.getContentType());

			try {
				image.setImage(new SerialBlob(file.getBytes()));
				StringBuilder downloadUrl = new StringBuilder("/api/v1/images/image/download/");
				image.setProduct(product);

				Image savedImage = imageRepository.save(image);
				downloadUrl.append(savedImage.getId());

				savedImage.setDownloadUrl(downloadUrl.toString());
				savedImage = imageRepository.save(savedImage);

				ImageDTO imageDto = new ImageDTO();
				imageDto.setId(savedImage.getId());
				imageDto.setFilename(savedImage.getFilename());
				imageDto.setFiletype(savedImage.getFiletype());
				imageDto.setDownloadUrl(savedImage.getDownloadUrl());
				savedImageDto.add(imageDto);

			} catch (SQLException | IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		});

		return savedImageDto;
	}

	@Override
	public void updateImage(MultipartFile multiPartFile, Long imageId) {
		Image image = getImageById(imageId);

		try {
			image.setFilename(multiPartFile.getOriginalFilename());
			image.setFiletype(multiPartFile.getContentType());
			image.setImage(new SerialBlob(multiPartFile.getBytes()));
		} catch (IOException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<ImageDTO> findByProductId(Long productId) {
		List<Image> imageList = imageRepository.findImageByProductId(productId);
		return imageList.stream().map(image -> modelMapper.map(imageList, ImageDTO.class)).toList();
	}

}
