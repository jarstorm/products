package com.product.rest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.OrderException;
import com.product.exception.ProductException;
import com.product.model.dto.OrderDto;
import com.product.rest.bean.ProductOrderVo;
import com.product.service.OrderService;

/**
 * Order controller.
 */
@Controller
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Method to cerate an order
	 * 
	 * @param productOrderVo order data
	 * @return created order id
	 * @throws ProductException exception
	 */
	@PutMapping(value = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody ProductOrderVo productOrderVo) throws ProductException {
		return orderService.create(productOrderVo.getUserEmail(), productOrderVo.getProducts());
	}

	/**
	 * Request orders between two dates.
	 * 
	 * @param startDate start date
	 * @param endDate   end date
	 * @return the orders between these dates
	 */
	@GetMapping(value = "/filter")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderDto> getOrders(@RequestParam @DateTimeFormat(pattern = "yyyyMMddHHmmss") Calendar startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyyMMddHHmmss") Calendar endDate) {
		return orderService.filterOrders(startDate, endDate);
	}

	/**
	 * Calculate amount of an order.
	 * @param orderId order id
	 * @return the amount for this order
	 * @throws OrderException exception
	 */
	@GetMapping(value = "/calculateAmount")
	@ResponseBody
	public BigDecimal calculateAmount(@RequestParam Long orderId) throws OrderException {
		return orderService.calculateAmount(orderId);
	}
}
