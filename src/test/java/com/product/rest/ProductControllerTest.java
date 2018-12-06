package com.product.rest;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.model.dto.ProductDto;

/**
 * Test ProductController class.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerTest extends AbstractControllerTest {

	/**
	 * Check product creation.
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void shouldAddProduct() throws Exception {
		// Mock order service method
		when(productService.createProduct("Product", new BigDecimal("99.99"))).thenReturn(1L);

		// Check REST method
		mockMvc.perform(put("/product/create?name=Product&price=99.99").contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().string("1"));
	}

	/**
	 * Check that the method could not be called due to price number has wrong
	 * format
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void shouldAddProductWrongFormat() throws Exception {
		// Check REST method
		mockMvc.perform(put("/product/create?name=Product&price=99,99").contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}

	/**
	 * Check that update method it's working.
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void shouldUpdateProduct() throws Exception {
		// Check REST method
		mockMvc.perform(post("/product/update?id=1&name=Product&price=199.99").contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * Check that get all products method it's working.
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void shouldGetAllProducts() throws Exception {
		// Mock order service method
		List<ProductDto> list = new ArrayList<>();
		list.add(new ProductDto(1L, "Name", new BigDecimal("99.99")));
		when(productService.getAll()).thenReturn(list);

		// Check REST method
		String expectedResponse = "[{\"id\":1,\"name\":\"Name\",\"price\":99.99}]";
		mockMvc.perform(get("/product").contentType(APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(expectedResponse));
	}

}