package com.cifer.ecommerce.repository;

import com.cifer.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.name FROM Product p")
    Optional<List<String>> getAllName();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findUsingName(String name);

    Optional<Product> findByName(String name);

    void deleteByName(String name);
}
