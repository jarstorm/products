package com.product.rest.bean;

/**
 * Product vo. Used to get data from products in REST API.
 */
public class ProductVo {

	private Long productId;

	private Long amount;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
