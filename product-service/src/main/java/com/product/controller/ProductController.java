package com.product.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private ProductService productService;
	
	ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@GetMapping("/health")
	public String healthCheck() {
		LOGGER.info("Inside healthCheck - ProductController");
		return "Product service is running.";
	}
	
	@PostMapping
	public String createProduct(@RequestBody ProductDTO productDTO) {
		LOGGER.info("Inside createProduct - ProductController: {}", productDTO);
		try {
			Product product = productService.createProduct(productDTO);
			return "Product created successfully with ID: " + product.getId();
		} catch (Exception e) {
			LOGGER.error("Error creating product: {}", e.getMessage());
			return "Error creating product: " + e.getMessage();
		}
	}
	
	@GetMapping
	public List<ProductDTO> getProducts() {
		LOGGER.info("Inside getProducts - ProductController");
		try {
			return productService.getAllProducts();
		}catch(Exception e) {
			LOGGER.error("Error fetching products: {}", e.getMessage());
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/{productId}")
	public ProductDTO getProductById(@PathVariable("productId") Long id) {
		LOGGER.info("Inside getProductById - ProductController: {}", id);
		try {
			return productService.getProductById(id);
		}catch(Exception e) {
			LOGGER.error("Error fetching product by ID: {}", e.getMessage());
			return null;
		}
	}
	
	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable("productId") Long id) {
		LOGGER.info("Inside deleteProduct - ProductController: {}", id);
		try {
			productService.deleteProduct(id);
			return "Product deleted successfully.";
		}catch(Exception e) {
			LOGGER.error("Error deleting product: {}", e.getMessage());
			return "Error deleting product: " + e.getMessage();
		}
	}
}
