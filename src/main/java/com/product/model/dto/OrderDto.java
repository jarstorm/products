package com.product.model.dto;

import java.util.Calendar;
import java.util.List;

import com.product.model.ProductOrder;

public class OrderDto {

	private Long id;

	private Calendar creationDate;

	private String userEmail;

	private List<ProductOrder> productOrders;

	public OrderDto(Long id, Calendar creationDate, String userEmail, List<ProductOrder> productOrders) {
		this.id = id;
		this.creationDate = creationDate;
		this.userEmail = userEmail;
		this.productOrders = productOrders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}

}
