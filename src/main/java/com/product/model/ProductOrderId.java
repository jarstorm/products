package com.product.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Product-order table composite id.
 */
@Embeddable
public class ProductOrderId implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -4929311373533458542L;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "order_id")
	private Long orderId;

	public ProductOrderId() {

	}

	public ProductOrderId(Long productId, Long orderId) {
		this.productId = productId;
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}