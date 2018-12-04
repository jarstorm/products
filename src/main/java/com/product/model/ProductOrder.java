package com.product.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "P_PRODUCT_ORDER")
public class ProductOrder {

	@EmbeddedId
	private ProductOrderId id;

	@Column(name = "PO_PRICE")
	private BigDecimal productPrice;

	@Column(name = "PO_AMOUNT")
	private Long amount;

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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
