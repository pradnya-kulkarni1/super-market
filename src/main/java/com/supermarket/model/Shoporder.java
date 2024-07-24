package com.supermarket.model;

import jakarta.persistence.*;

@Entity
public class Shoporder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cartId")
	public Cart cart;
	
	private String status;
	private String paymentMode;
	private boolean paymentDone;
	private boolean devilvered;
	public Shoporder() {
		super();
	}
	public Shoporder(int id, Cart cart, String status, String paymentMode, boolean paymentDone, boolean devilvered) {
		super();
		this.id = id;
		this.cart = cart;
		this.status = status;
		this.paymentMode = paymentMode;
		this.paymentDone = paymentDone;
		this.devilvered = devilvered;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public boolean isPaymentDone() {
		return paymentDone;
	}
	public void setPaymentDone(boolean paymentDone) {
		this.paymentDone = paymentDone;
	}
	public boolean isDevilvered() {
		return devilvered;
	}
	public void setDevilvered(boolean devilvered) {
		this.devilvered = devilvered;
	}
	@Override
	public String toString() {
		return "Shoporder [id=" + id + ", cart=" + cart + ", status=" + status + ", paymentMode=" + paymentMode
				+ ", paymentDone=" + paymentDone + ", devilvered=" + devilvered + "]";
	}
	
	
	

}
