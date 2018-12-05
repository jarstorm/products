package com.product.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.exception.ProductException;
import com.product.model.Order;
import com.product.model.ProductOrder;
import com.product.repository.OrderRepository;
import com.product.repository.ProductRepository;
import com.product.rest.bean.ProductVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Before
	public void setUp() {
		orderReporitory.deleteAll();
		productReporitory.deleteAll();
	}

	@Autowired
	private ProductRepository productReporitory;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderRepository orderReporitory;

	@Autowired
	private OrderService orderService;

	@Test
	public void filter() {
		Assert.fail("Not implemented yet");
	}

	@Test
	public void createOrder() throws ProductException {

		// Create new product
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));

		String userEmail = "a@a.com";
		List<ProductVo> products = new ArrayList<>();
		ProductVo product = new ProductVo();
		product.setAmount(10L);
		product.setProductId(productId);
		products.add(product);
		Long orderId = orderService.create(userEmail, products);

		// Check that the id is not null
		assertThat("Order id shouldn't be null", orderId, notNullValue());

		// Check order data is correct
		Optional<Order> orderOptional = orderReporitory.findById(orderId);
		if (orderOptional.isPresent()) {
			Order order = orderOptional.get();
			assertThat("Order id should be the same", order.getId(), equalTo(orderId));
			assertThat("Order user email should be the same", order.getUserEmail(), equalTo("a@a.com"));
			assertThat("Order products should be the same", order.getProductOrders().size(), equalTo(1));
			assertThat("Order creation date should not be null", order.getCreationDate(), notNullValue());
			for (ProductOrder po: order.getProductOrders()) {
				assertThat("Product order amount should be the same", po.getAmount(), equalTo(10L));
				assertThat("Product order price should be the same", po.getProductPrice(), equalTo(new BigDecimal("99.99")));
			}
		} else {
			Assert.fail("Could not be empty");
		}
	}

	@Test
	public void shouldNotCreateOrderWithWrongEmailFormat() {
		Assert.fail("Not implemented yet");
	}

	@Test
	public void calculateOrderAmount() {
		Assert.fail("Not implemented yet");
	}

	@Test
	public void calculateOrderAmountAfterPriceChange() {
		Assert.fail("Not implemented yet");
	}
}