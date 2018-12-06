package com.product.service;

import java.math.BigDecimal;
import java.util.List;

import com.product.exception.ProductException;
import com.product.model.dto.ProductDto;

/**
 * Product service.
 */
public interface ProductService {

	/**
	 * Create product.
	 * 
	 * @param name  product name
	 * @param price product price
	 * @return the id of the product
	 */
	Long createProduct(String name, BigDecimal price);

	/**
	 * Update product
	 * 
	 * @param id    product id
	 * @param name  product name
	 * @param price product price
	 * @throws ProductException exception
	 */
	void updateProduct(Long id, String name, BigDecimal price) throws ProductException;

	/**
	 * Get all products.	
	 * @return the list of every product
	 */
	List<ProductDto> getAll();

}
