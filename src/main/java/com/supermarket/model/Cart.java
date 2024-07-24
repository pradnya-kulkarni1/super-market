package com.supermarket.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	public User user;
	
	@ManyToOne
	@JoinColumn(name="productId")
	public Product product;
	
	private float totalAmount;
	private String deliveryMode;
	private LocalDateTime submittedDate;
	private String status;
	private boolean checkOut;
	public Cart() {
		super();
	}
	public Cart(int id, User user, Product product, float totalAmount, String deliveryMode, LocalDateTime submittedDate,
			String status, boolean checkOut) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.totalAmount = totalAmount;
		this.deliveryMode = deliveryMode;
		this.submittedDate = submittedDate;
		this.status = status;
		this.checkOut = checkOut;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isCheckOut() {
		return checkOut;
	}
	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", product=" + product + ", totalAmount=" + totalAmount
				+ ", deliveryMode=" + deliveryMode + ", submittedDate=" + submittedDate + ", status=" + status
				+ ", checkOut=" + checkOut + "]";
	}
	
	
	

}
