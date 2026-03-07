package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

   // derived query methods:
     List<Product> findByCategory(String category);
    List<Product> findByPriceBetween(double min, double max);

    // JPQL queries using @Query
     //a. Sort products by price
    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> sortByPrice();

    // b. Fetch products above a price
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensiveProducts(double price);

    // c. Fetch products by category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> fetchByCategory(String category);
}

