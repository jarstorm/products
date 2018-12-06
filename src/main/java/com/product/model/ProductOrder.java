package com.product.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Relation product-order entity. Used to storage product price at this moment
 * (used to recalculate order amount) and units
 */
@Entity
@Table(name = "P_PRODUCT_ORDER")
public class ProductOrder {

	@EmbeddedId
	private ProductOrderId id;

	@Column(name = "PO_PRICE")
	private BigDecimal productPrice;

	@Column(name = "PO_UNITS")
	private Long units;

	public ProductOrder() {

	}

	public ProductOrder(Long productId, Long orderId) {
		this.id = new ProductOrderId(productId, orderId);
	}

	public ProductOrderId getId() {
		return id;
	}

	public void setId(ProductOrderId id) {
		this.id = id;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Long getUnits() {
		return units;
	}
	
	public void setUnits(Long units) {
		this.units = units;
	}
}
