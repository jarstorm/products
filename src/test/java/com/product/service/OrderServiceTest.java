package com.product.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.exception.OrderException;
import com.product.exception.ProductException;
import com.product.model.Order;
import com.product.model.ProductOrder;
import com.product.repository.OrderRepository;
import com.product.repository.ProductRepository;
import com.product.rest.bean.ProductVo;
import com.product.service.impl.OrderServiceImpl;
import com.product.service.impl.ProductServiceImpl;

/**
 * Test OrderService class.
 */
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
	private ProductServiceImpl productService;

	@Autowired
	private OrderRepository orderReporitory;

	@Autowired
	private OrderServiceImpl orderService;

	/**
	 * Test that filter method it's working properly. We created two orders (on for
	 * one hour before) and we are looking only for the last minute. Then it should
	 * only find one.
	 */
	@Test
	public void filterItsWorkingOk() {
		// Create one order for one hour ago
		Order order1 = new Order();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.HOUR, -1);
		order1.setCreationDate(calendar1);
		order1.setUserEmail("a@a.com");
		orderReporitory.save(order1);

		// Create one order for now
		Order order2 = new Order();
		Calendar calendar2 = Calendar.getInstance();
		order2.setCreationDate(calendar2);
		order2.setUserEmail("a@a.com");
		orderReporitory.save(order2);

		// Find the orders in the last minute
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.MINUTE, -1);
		Calendar endDate = Calendar.getInstance();
		List<Order> orders = orderReporitory.filterByDates(startDate, endDate);

		assertThat("There should find only one order", orders.size(), equalTo(1));
	}

	/**
	 * Test that filter method it's working properly. We created two orders (on for
	 * one minute before) and we are looking for the last hour. Then it should only
	 * find both of them.
	 */
	@Test
	public void filterItsWorkingOk2() {
		// Create one order for one minute ago
		Order order1 = new Order();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MINUTE, -1);
		order1.setCreationDate(calendar1);
		order1.setUserEmail("a@a.com");
		orderReporitory.save(order1);

		// Create one order for now
		Order order2 = new Order();
		Calendar calendar2 = Calendar.getInstance();
		order2.setCreationDate(calendar2);
		order2.setUserEmail("a@a.com");
		orderReporitory.save(order2);

		// Find the orders in the last minute
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.HOUR, -1);
		Calendar endDate = Calendar.getInstance();
		List<Order> orders = orderReporitory.filterByDates(startDate, endDate);

		assertThat("There should find only one order", orders.size(), equalTo(2));
	}

	/**
	 * Test create order method.
	 * 
	 * @throws ProductException exception
	 */
	@Test
	public void createOrder() throws ProductException {

		// Create a product for this order
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));

		// Create new order
		Long orderId = createOrderObject(productId);

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
			for (ProductOrder po : order.getProductOrders()) {
				assertThat("Product order amount should be the same", po.getUnits(), equalTo(10L));
				assertThat("Product order price should be the same", po.getProductPrice(),
						equalTo(new BigDecimal("99.99")));
			}
		} else {
			Assert.fail("Could not be empty");
		}
	}

	/**
	 * Create an order object.
	 * 
	 * @param productId product id
	 * @return Order object
	 * @throws ProductException exception
	 */
	private Long createOrderObject(Long productId) throws ProductException {
		String userEmail = "a@a.com";
		List<ProductVo> products = new ArrayList<>();
		ProductVo product = new ProductVo();
		product.setAmount(10L);
		product.setProductId(productId);
		products.add(product);
		Long orderId = orderService.create(userEmail, products);
		return orderId;
	}

	/**
	 * Test that user email could nod be null.
	 * 
	 * @throws ProductException exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createOrderNullMail() throws ProductException {
		orderService.create(null, new ArrayList<>());
	}

	/**
	 * Test that user email could not be empty.
	 * 
	 * @throws ProductException exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createOrderEmptyMail() throws ProductException {
		orderService.create("", new ArrayList<>());
	}

	/**
	 * Test that user email should have a good email format.
	 * 
	 * @throws ProductException exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createOrderWrongMailFormat() throws ProductException {
		orderService.create("1-9", new ArrayList<>());
	}

	/**
	 * Test that product list should not be empty.
	 * 
	 * @throws ProductException exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createOrderEmptyProducts() throws ProductException {
		orderService.create("a@a.com", new ArrayList<>());
	}

	/**
	 * Test calculate amount.
	 * 
	 * @throws ProductException exception
	 * @throws OrderException   exception
	 */
	@Test
	public void calculateOrderAmount() throws ProductException, OrderException {
		// Create product and order
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));
		Long orderId = createOrderObject(productId);

		// Calculate order amount
		BigDecimal amount = orderService.calculateAmount(orderId);

		// Check data
		assertThat("Order amount should be the same", amount, equalTo(new BigDecimal("999.90")));
	}

	/**
	 * Test calculate amount before product price update.
	 * 
	 * @throws ProductException exception
	 * @throws OrderException   exception
	 */
	@Test
	public void calculateOrderAmountAfterPriceChange() throws ProductException, OrderException {
		// Create product and order
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));
		Long orderId = createOrderObject(productId);

		// Calculate amount before update
		BigDecimal amountBefore = orderService.calculateAmount(orderId);

		// Update product
		productService.updateProduct(productId, "New name", new BigDecimal("199.99"));

		// Calculate amount after update
		BigDecimal amountAfter = orderService.calculateAmount(orderId);

		// Test data
		assertThat("Order amount should be the same", amountAfter, equalTo(amountBefore));

	}
}