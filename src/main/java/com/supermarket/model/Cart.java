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
	
	private float totalAmount;
	private String deliveryMode;
	private LocalDateTime submittedDate;
	private String status;
	private boolean checkout;
	private boolean isEmpty;
	
	public Cart() {
		super();
	}
	
	public Cart(int id, User user, float totalAmount, String deliveryMode, LocalDateTime submittedDate, String status,
			boolean checkout, boolean isEmpty) {
		super();
		this.id = id;
		this.user = user;
		this.totalAmount = totalAmount;
		this.deliveryMode = deliveryMode;
		this.submittedDate = submittedDate;
		this.status = status;
		this.checkout = checkout;
		this.isEmpty = isEmpty;
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

	public boolean isCheckout() {
		return checkout;
	}

	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", totalAmount=" + totalAmount + ", deliveryMode=" + deliveryMode
				+ ", submittedDate=" + submittedDate + ", status=" + status + ", checkout=" + checkout + ", isEmpty="
				+ isEmpty + "]";
	}

	
	
}
