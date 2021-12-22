package com.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT m FROM Product m WHERE m.name LIKE %:name%")
	Page<Product> findByName(@RequestParam("name") String name, Pageable  pageable);
}
