package com.product.model.dto;

import java.util.Calendar;
import java.util.List;

import com.product.model.ProductOrder;

/**
 * Order DTO. Used to send data from database to REST API
 */
public class OrderDto {

	/**
	 * Order id.
	 */
	private Long id;

	/**
	 * Order creation date.
	 */
	private Calendar creationDate;

	/**
	 * User email.
	 */
	private String userEmail;

	/**
	 * Product orders.
	 */
	private List<ProductOrder> productOrders;

	/**
	 * Public constructor.
	 * 
	 * @param id            order id
	 * @param creationDate  creation date
	 * @param userEmail     user email
	 * @param productOrders product orders
	 */
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
