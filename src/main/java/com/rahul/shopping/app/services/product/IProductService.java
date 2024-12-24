package com.rahul.shopping.app.services.product;

import java.util.List;

import com.rahul.shopping.app.dto.ProductDTO;
import com.rahul.shopping.app.model.Product;
import com.rahul.shopping.app.request.ProductRequest;

public interface IProductService {
	
	ProductDTO addProduct(ProductRequest produuctRequest);
	
	ProductDTO getProductById(Long productId);
	
	void deleteProductById(Long productId);
	
	void updateProduct(ProductRequest request, Long productId);
	
	List<ProductDTO> getAllProducts();
	
	List<ProductDTO> getProductsByCategory(String category);
	
	List<ProductDTO> getProductsByBrand(String brand);
	
	List<ProductDTO> getProductsByCategoryAndBrand(String cagtegory, String brand);
	
	List<ProductDTO> getProductsByName(String name);
	
	List<ProductDTO> getProductsByBrandAndName(String brand, String name);
	
	Long countProductsByBrandAndName(String brand, String name);
	
	
	

}
