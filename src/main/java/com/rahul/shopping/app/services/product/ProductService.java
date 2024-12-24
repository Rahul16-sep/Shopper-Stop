package com.rahul.shopping.app.services.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.shopping.app.dto.ImageDTO;
import com.rahul.shopping.app.dto.ProductDTO;
import com.rahul.shopping.app.exception.ProductNotFoundException;
import com.rahul.shopping.app.model.Category;
import com.rahul.shopping.app.model.Image;
import com.rahul.shopping.app.model.Product;
import com.rahul.shopping.app.repository.CategoryRepository;
import com.rahul.shopping.app.repository.ImageRepository;
import com.rahul.shopping.app.repository.ProductRepository;
import com.rahul.shopping.app.request.ProductRequest;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO addProduct(ProductRequest product) {
		
		Category category = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()))
			.orElseGet(() -> {
				Category newCategory = new Category(product.getCategory().getName());
				return categoryRepository.save(newCategory);
			});
		
		return getProductDTO(productRepository.save(createProduct(product, category)));

	}
	
	
	public Product createProduct(ProductRequest request, Category category) {
		Product product = new Product(
							request.getName(),
							request.getBrand(),
							request.getDescription(),
							request.getPrice(),
							request.getInventory(),
							category);
		
		return product;
	}
	

	@Override
	public ProductDTO getProductById(Long productId) {
		return productRepository.findById(productId)
				.map(product -> getProductDTO(product))
				.orElseThrow(() ->
						{ throw new ProductNotFoundException("Product Not found !");});
	}
	

	@Override
	public void deleteProductById(Long productId) {
		productRepository.findById(productId)
				.ifPresentOrElse(productRepository::delete, () -> 
					  {throw new ProductNotFoundException("");});
		
	}
	
	
	@Override
	public void updateProduct(ProductRequest productRequest, Long productId) {
		productRepository.findById(productId)
		.map(existingProduct -> updateExistingProduct(existingProduct, productRequest))
		.map(productRepository :: save)
		.orElseThrow(() -> new ProductNotFoundException("Product is not Found"));
		
	}
	
	
	private Product updateExistingProduct(Product existingProduct, ProductRequest productRequest) {
			existingProduct.setBrand(productRequest.getBrand());
			existingProduct.setName(productRequest.getName());
			existingProduct.setDescription(productRequest.getDescription());
			existingProduct.setInventory(productRequest.getInventory());
			
			Category category = categoryRepository.findByName(productRequest.getCategory().getName());
			existingProduct.setCategory(category);
			
			return existingProduct;
	}
	

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}
	

	@Override
	public List<ProductDTO> getProductsByCategory(String category) {
		return productRepository.findByCategoryName(category).stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}
	

	@Override
	public List<ProductDTO> getProductsByBrand(String brand) {
		return productRepository.findByBrand(brand).stream()
				.map(product ->  getProductDTO(product))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<ProductDTO> getProductsByCategoryAndBrand(String cagtegory, String brand) {
		return productRepository.findByCategoryNameAndBrand(cagtegory, brand).stream()
				.map(product ->  getProductDTO(product))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<ProductDTO> getProductsByName(String name) {
		return productRepository.findByName(name)
				.stream().map(product -> getProductDTO(product)).toList();
	}
	

	@Override
	public List<ProductDTO> getProductsByBrandAndName(String brand, String name) {
		return productRepository.findByBrandAndName(brand, name).stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}
	

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		return productRepository.countByBrandAndName(brand, name);
	}

	
	private ProductDTO getProductDTO(Product product) {
		ProductDTO productDTO =  modelMapper.map(product, ProductDTO.class);
		List<Image> images = imageRepository.findImageByProductId(product.getId());
		System.out.println(images.size());
		images.stream().forEach(im -> System.out.println(im.getId()));
		List<ImageDTO> imageDTOs = images.stream()
				.map(image -> modelMapper.map(image, ImageDTO.class))
				.toList();
		productDTO.setImageDTOs(imageDTOs);
		return productDTO;
	}

}
