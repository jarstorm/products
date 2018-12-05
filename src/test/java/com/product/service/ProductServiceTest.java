package com.product.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.model.dto.ProductDto;
import com.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Before
	public void setUp() {
		productReporitory.deleteAll();
	}

	@Autowired
	private ProductRepository productReporitory;

	@Autowired
	private ProductService productService;

	/**
	 * Add product test. Check data in database
	 */
	@Test
	public void shouldAddProduct() {
		// Create new product
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));

		// Check that the id is not null
		assertThat("Product id shouldn't be null", productId, notNullValue());

		// Check product database data
		Optional<Product> productOptional = productReporitory.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			assertThat("Product id should be the same", product.getId(), equalTo(productId));
			assertThat("Product name should be the same", product.getName(), equalTo("Name"));
			assertThat("Product price should be the same", product.getPrice(), equalTo(new BigDecimal("99.99")));
		} else {
			Assert.fail("Could not be empty");
		}
	}

	/**
	 * Should not add a product with a price lower than 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldNotAddProductPriceLower0() {
		productService.createProduct("Name", new BigDecimal("-1"));
	}

	/**
	 * Testing product update
	 *
	 * @throws ProductException if there is no product with this id
	 */
	@Test
	public void shouldUpdateProduct() throws ProductException {
		// Create new product
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));

		// Update it
		productService.updateProduct(productId, "New name", new BigDecimal("199.99"));

		// Check product database data
		Optional<Product> productOptional = productReporitory.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			assertThat("Product id should be the same", product.getId(), equalTo(productId));
			assertThat("Product name should be the same", product.getName(), equalTo("New name"));
			assertThat("Product price should be the same", product.getPrice(), equalTo(new BigDecimal("199.99")));
		} else {
			Assert.fail("Could not be empty");
		}
	}

	/**
	 * Testing product update. Cannot be updated because this id will never be in database
	 *
	 * @throws ProductException if there is no product with this id
	 */
	@Test(expected = ProductException.class)
	public void shouldUpdateProductError() throws ProductException {
		// Update a non existing id
		productService.updateProduct(-1L, "Name", new BigDecimal("99.99"));
	}

	/**
	 * Return every product.
	 */
	@Test
	public void getAll() {
		// Check that the list is empty
		List<ProductDto> products = productService.getAll();
		assertThat("List is empty", 0, equalTo(products.size()));

		// Add an item
		productService.createProduct("Name", new BigDecimal("99.99"));

		// Check that the list is not empty
		products = productService.getAll();
		assertThat("List is not empty", products.size(), equalTo(1));
	}

}