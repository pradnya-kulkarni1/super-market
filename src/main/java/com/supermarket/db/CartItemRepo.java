package com.supermarket.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.model.Cart;
import com.supermarket.model.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Integer>{

	List<CartItem> findByCart(Cart c);

}
