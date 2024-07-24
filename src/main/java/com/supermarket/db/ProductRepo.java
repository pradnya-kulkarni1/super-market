package com.supermarket.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
