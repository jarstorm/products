package com.product.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.product.exception.OrderException;
import com.product.exception.ProductException;
import com.product.model.dto.OrderDto;
import com.product.rest.bean.ProductVo;

/**
 * Order service.
 */
public interface OrderService {

	/**
	 * Return a list of orders between two dates.
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @return the list of orders
	 */
	List<OrderDto> filterOrders(Calendar startDate, Calendar endDate);

	/**
	 * Create an order,
	 * 
	 * @param userEmail user email
	 * @param products  list of products
	 * @return the id of the order
	 * @throws ProductException exception
	 */
	Long create(String userEmail, List<ProductVo> products) throws ProductException;

	/**
	 * Calculate order amount.
	 * 
	 * @param orderId order id
	 * @return the amount of this order
	 * @throws OrderException exception
	 */
	BigDecimal calculateAmount(Long orderId) throws OrderException;
}
