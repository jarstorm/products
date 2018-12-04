package com.product.service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.OrderException;
import com.product.exception.ProductException;
import com.product.model.*;
import com.product.model.dto.OrderDto;
import com.product.repository.OrderRepository;
import com.product.repository.ProductRepository;
import com.product.rest.bean.ProductVo;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<OrderDto> filterOrders(Calendar startDate, Calendar endDate) {
		return orderRepository.filterByDates(startDate, endDate).parallelStream().map(order -> new OrderDto(order.getId(), order
				.getCreationDate(), order.getUserEmail(), order.getProductOrders())).collect(Collectors.toList());
	}

	public void create(String userEmail, List<ProductVo> products) throws ProductException {
		Order order = saveOrder(userEmail);

		List<ProductOrder> productOrders = new ArrayList<>();
		for (ProductVo productVo : products) {
			Product product = productRepository.getOne(productVo.getProductId());
			if (product == null) {
				throw new ProductException("Product with id " + productVo.getProductId() + " not found");
			}
			ProductOrder productOrder = new ProductOrder(product.getId(), order.getId());
			productOrder.setAmount(productVo.getAmount());
			productOrder.setProductPrice(product.getPrice());
			productOrders.add(productOrder);
		}
		order.setProductOrders(productOrders);
		orderRepository.save(order);
	}

	private Order saveOrder(String userEmail) {
		Order order = new Order();
		order.setCreationDate(Calendar.getInstance());
		order.setUserEmail(userEmail);
		order = orderRepository.save(order);
		return order;
	}

	public BigDecimal calculateAmount(Long orderId) throws OrderException {
		Order order = orderRepository.getOne(orderId);
		if (order == null) {
			throw new OrderException("Order with id " + orderId + " not found");
		}
		BigDecimal amount = new BigDecimal(0);
		for (ProductOrder productOrder : order.getProductOrders()) {
			amount = amount.add(productOrder.getProductPrice().multiply(new BigDecimal(productOrder.getAmount())));
		}
		return amount;
	}

}
