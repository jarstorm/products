package com.product.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
public class Order {

	@Id
	@GeneratedValue
	private Long id;

	private String userEmail;

	private LocalDateTime creationDate;

	@OneToMany
	private List<ProductOrder> productOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}


}
