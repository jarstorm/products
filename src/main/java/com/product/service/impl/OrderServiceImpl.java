package com.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.OrderException;
import com.product.exception.ProductException;
import com.product.model.Order;
import com.product.model.Product;
import com.product.model.ProductOrder;
import com.product.model.dto.OrderDto;
import com.product.repository.OrderRepository;
import com.product.repository.ProductRepository;
import com.product.rest.bean.ProductVo;
import com.product.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<OrderDto> filterOrders(Calendar startDate, Calendar endDate) {
		return orderRepository.filterByDates(startDate, endDate).parallelStream().map(order -> new OrderDto(order.getId(), order
				.getCreationDate(), order.getUserEmail(), order.getProductOrders())).collect(Collectors.toList());
	}

	@Override
	public Long create(String userEmail, List<ProductVo> products) throws ProductException {
		if (userEmail == null || userEmail.isEmpty()) {
			throw new IllegalArgumentException("User email should not ne empty");
		}

		if (!EmailValidator.getInstance().isValid(userEmail)) {
			throw new IllegalArgumentException("User email has no correct format");
		}


		if (products == null || products.isEmpty()) {
			throw new IllegalArgumentException("Products should not ne empty");
		}

		Order order = saveOrder(userEmail);

		List<ProductOrder> productOrders = new ArrayList<>();
		for (ProductVo productVo : products) {
			Optional<Product> productOptional = productRepository.findById(productVo.getProductId());
			if (!productOptional.isPresent()) {
				throw new ProductException("Product with id " + productVo.getProductId() + " not found");
			}
			Product product = productOptional.get();
			ProductOrder productOrder = new ProductOrder(product.getId(), order.getId());
			productOrder.setUnits(productVo.getAmount());
			productOrder.setProductPrice(product.getPrice());
			productOrders.add(productOrder);
		}
		order.setProductOrders(productOrders);
		order = orderRepository.save(order);
		return order.getId();
	}

	private Order saveOrder(String userEmail) {
		Order order = new Order();
		order.setCreationDate(Calendar.getInstance());
		order.setUserEmail(userEmail);
		order = orderRepository.save(order);
		return order;
	}

	@Override
	public BigDecimal calculateAmount(Long orderId) throws OrderException {
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if (!orderOptional.isPresent()) {
			throw new OrderException("Order with id " + orderId + " not found");
		}
		Order order = orderOptional.get();
		BigDecimal amount = new BigDecimal(0);
		for (ProductOrder productOrder : order.getProductOrders()) {
			amount = amount.add(productOrder.getProductPrice().multiply(new BigDecimal(productOrder.getUnits())));
		}
		return amount;
	}

}
