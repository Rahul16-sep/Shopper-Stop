package com.rahul.shopping.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.rahul.shopping.app.dto.ProductDTO;
import com.rahul.shopping.app.exception.ProductNotFoundException;
import com.rahul.shopping.app.model.Product;
import com.rahul.shopping.app.request.ProductRequest;
import com.rahul.shopping.app.response.APIResponse;
import com.rahul.shopping.app.services.product.IProductService;

@RestController
@RequestMapping("${api.prefix}/products/")
public class ProductController {

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@Autowired
	private final IProductService productService;

	@PostMapping("/add/")
	public ResponseEntity<APIResponse> addProduct(@RequestBody ProductRequest productRequest) {
		ProductDTO product = productService.addProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse("Product Added successfully", product));
	}

	@GetMapping("/get/{id}/product/by-id/")
	public ResponseEntity<APIResponse> getProductById(@PathVariable Long id) {
		try {
			ProductDTO product = productService.getProductById(id);
			return ResponseEntity.status(HttpStatus.FOUND).body(new APIResponse("Product Found", product));
		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Product Not Found", null));
		}
	}

	@DeleteMapping("/delete/{id}/product/")
	public ResponseEntity<APIResponse> deleteProduct(@PathVariable("id") Long productId) {
		try {
			productService.deleteProductById(productId);
			return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Deleted Successfully", null));
		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("Error in Deletion", e.getMessage()));
		}
	}

	@PutMapping("/update/{id}/product/")
	public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductRequest productRequest,
			@PathVariable Long id) {
		try {
			productService.updateProduct(productRequest, id);
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new APIResponse("Product Updated Successfully", null));
		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("Error in Deletion", e.getMessage()));
		}
	}

	@GetMapping("/get/all/")
	public ResponseEntity<APIResponse> getAllProducts() {
		try {
			List<ProductDTO> allProducts = productService.getAllProducts();
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", allProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}

	}

	@GetMapping("/get/{category}/product/by-category/")
	public ResponseEntity<APIResponse> getAllProductsByCategory(@PathVariable("category") String categoryName) {
		try {
			List<ProductDTO> allProducts = productService.getProductsByCategory(categoryName);
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", allProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}
	}

	@GetMapping("/get/product/by-brand/")
	public ResponseEntity<APIResponse> getAllProductsByBrand(@RequestParam("brand") String brandName) {
		try {
			List<ProductDTO> allProducts = productService.getProductsByBrand(brandName);
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", allProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}
	}

	@GetMapping("/get/{category}/{brand}/product/by-category-and-brand/")
	public ResponseEntity<APIResponse> getAllProductsByCategoryAndBrand(@PathVariable("category") String categoryName,
			@PathVariable("brand") String brandName) {
		try {
			List<ProductDTO> allProducts = productService.getProductsByCategoryAndBrand(categoryName, brandName);
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", allProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}
	}
	
	@GetMapping("/get/{name}/product/by-name/")
	public ResponseEntity<APIResponse> getAllProductsByName(@PathVariable("name") String productName) {
		try {
			List<ProductDTO> allProducts = productService.getProductsByName(productName);
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", allProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}
	}
	
	@GetMapping("/get/{name}/{brand}/product/by-name-and-brand/")
	public ResponseEntity<APIResponse> getAllProductsByNameAndBrand(@PathVariable("name") String productName,
			@PathVariable("brand") String brandName) {
		try {
			List<ProductDTO> allProducts = productService.getProductsByCategoryAndBrand(productName, brandName);
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", allProducts));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}
	}
	
	@GetMapping("/get/count/{name}/{brand}/product/by-name-and-brand/")
	public ResponseEntity<APIResponse> getCountByBrandAndName(@PathVariable("name") String productName,
			@PathVariable("brand") String brandName) {
		try {
			Long count = productService.countProductsByBrandAndName(productName, brandName);
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new APIResponse("Found All Products Successfully", count));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new APIResponse("INTERNAL SERVER ERROR", e.getMessage()));
		}
	}

}
