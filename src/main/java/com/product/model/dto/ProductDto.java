package com.product.model.dto;

import java.math.BigDecimal;

/**
 * Product DTO. Used to send data from database to REST API
 */
public class ProductDto {

	/**
	 * Product id.
	 */
	private Long id;

	/**
	 * Product name.
	 */
	private String name;

	/**
	 * Product price.
	 */
	private BigDecimal price;

	/**
	 * Default constructor.
	 */
	public ProductDto() {

	}

	/**
	 * Constructor.
	 * 
	 * @param id    product id
	 * @param name  product name
	 * @param price product priceO
	 */
	public ProductDto(Long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

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
