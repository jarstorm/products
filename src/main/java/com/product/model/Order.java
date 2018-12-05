package com.product.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "P_ORDER")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "ORDER_EMAIL")
	private String userEmail;

	@Column(name = "ORDER_DATE")
	private Calendar creationDate;

	@OneToMany(mappedBy = "id.orderId", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
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

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}

}
