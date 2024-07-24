package com.supermarket.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
