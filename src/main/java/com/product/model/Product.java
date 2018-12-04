package com.product.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "P_PRODUCT")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private Long id;

	@Column(name = "PRODUCT_NAME")
	private String name;

	@Column(name = "PRODUCT_PRICE")
	private BigDecimal price;

	@OneToMany(mappedBy = "id.productId", cascade = CascadeType.ALL)
	private List<ProductOrder> productOrder;

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

	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}

}
