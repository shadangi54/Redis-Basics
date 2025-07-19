package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.product.dao.ProductDAO;
import com.product.dto.ProductDTO;
import com.product.entity.Product;

@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	private ProductDAO productDAO;

	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Caching(put = { @CachePut(value = "PRODUCTS_CACHE", key = "#result.id")},
			evict = {@CacheEvict(value = "ALL_PRODUCTS_CACHE", allEntries = true) })
	public Product createProduct(ProductDTO productDTO) {
		LOGGER.info("Creating product with details: {}", productDTO);
		Product product;
		if (productDTO.getId() != null) {
			product = productDAO.getById(productDTO.getId());
		} else {
			product = new Product();
		}

		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		return productDAO.save(product);
	}

	@Cacheable(value = "ALL_PRODUCTS_CACHE")
	public List<ProductDTO> getAllProducts() {
		LOGGER.info("Fetching all products from the database.");
		List<Product> products = productDAO.findAll();
		if (products.isEmpty()) {
			LOGGER.info("No products found.");
			return List.of();
		}

		List<ProductDTO> productDTOList = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice());
			productDTOList.add(productDTO);
		}

		return productDTOList;
	}

	@Cacheable(value = "PRODUCTS_CACHE", key = "#id")
	public ProductDTO getProductById(Long id) {
		LOGGER.info("Fetching product by ID: {}", id);
		Product product = productDAO.getById(id);

		return new ProductDTO(product.getId(), product.getName(), product.getPrice());
	}

	@Caching(evict = { @CacheEvict(value = "PRODUCTS_CACHE", key = "#id"),
			@CacheEvict(value = "ALL_PRODUCTS_CACHE", allEntries = true) })
	public void deleteProduct(Long id) {
		LOGGER.info("Deleting product with ID: {}", id);
		productDAO.deleteById(id);
	}

}
