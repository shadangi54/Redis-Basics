package com.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
}
