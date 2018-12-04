package com.product.rest.bean;

import java.util.List;

public class ProductOrderVo {

	private String userEmail;

	private List<ProductVo> products;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<ProductVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVo> products) {
		this.products = products;
	}



}
