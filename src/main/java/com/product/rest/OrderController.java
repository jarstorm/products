package com.product.rest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.product.exception.OrderException;
import com.product.exception.ProductException;
import com.product.model.dto.OrderDto;
import com.product.rest.bean.ProductOrderVo;
import com.product.service.OrderService;

@Controller
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PutMapping(value = "/create")
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody ProductOrderVo productOrderVo) throws ProductException {
		orderService.create(productOrderVo.getUserEmail(), productOrderVo.getProducts());
	}

	@GetMapping(value = "/filter")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderDto> getOrders(@RequestParam @DateTimeFormat(pattern = "yyyyMMddHHmmss") Calendar startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyyMMddHHmmss") Calendar endDate) {
		return orderService.filterOrders(startDate, endDate);
	}

	@GetMapping(value = "/calculateAmount")
	@ResponseBody
	public BigDecimal calculateAmount(@RequestParam Long orderId) throws OrderException {
		return orderService.calculateAmount(orderId);
	}
}
