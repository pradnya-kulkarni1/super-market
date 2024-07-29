package com.supermarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	public User user;

	@ManyToOne
	@JoinColumn(name="cartId")
	public Cart cart;
	
	@ManyToOne
	@JoinColumn(name="productId")
	public Product product;

	public CartItem() {
		super();
	}

	public CartItem(int id, User user, Cart cart, Product product) {
		super();
		this.id = id;
		this.user = user;
		this.cart = cart;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", user=" + user + ", cart=" + cart + ", product=" + product + "]";
	}
	
	
}
