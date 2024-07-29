package com.supermarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.supermarket.db.CartRepo;
import com.supermarket.db.ProductRepo;
import com.supermarket.model.Cart;
import com.supermarket.model.Product;

@CrossOrigin
@RestController
@RequestMapping("/api/carts")
public class CartController {
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@GetMapping("/")
	public List<Cart> getAllCarts() {
		return cartRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Cart getCarttById(@PathVariable int id) {
		Optional<Cart> c = cartRepo.findById(id);

		if (c.isPresent()) {
			return c.get();
		} else {
			System.err.println("Cart[" + id + "]does not exist");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found: id [" + id + "]");
		}
	}
		
	@PutMapping("/{id}")
	public Cart UpdateCart(@PathVariable int id, @RequestBody Cart cart) {
			Cart c = null;
			if (id != cart.getId()) {
				System.err.println("CartId [" + c.getId() + "] does not match cart id[" + id + "].");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart not found");
			} else if (!cartRepo.existsById(id)) {
				System.err.println("Cart does not exist for id: " + id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart does not exist");
			} else
				try {
					c = cartRepo.save(cart);
				} catch (Exception e) {
					System.err.println(e);
					throw e;
				}
			return c;
		}

		@DeleteMapping("/{id}")
		public boolean deleteCart(@PathVariable int id) {
			boolean success = false;

			if (cartRepo.existsById(id)) {
				cartRepo.deleteById(id);
				success = true;
			} else {
				System.err.println("Delete Error: No cart exists for id: " + id);
				success = false;
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cart does not exist");
			}
			return success;
		}
	}



