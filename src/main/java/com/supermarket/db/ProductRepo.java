package com.supermarket.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	//List<Product> findByCategory();

}
