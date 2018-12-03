package com.product.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private BigDecimal price;

	@OneToOne
	private ProductOrder productOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
