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
import org.springframework.http.MediaType;

import com.product.model.dto.ProductDto;

public class ProductControllerTest extends AbstractControllerTest {

	@Test
	public void shouldAddProduct() throws Exception {
		addProduct();
	}

	private void addProduct() throws Exception {
		// when
		when(productService.createProduct("Product", new BigDecimal("99.99"))).thenReturn(1L);

		// then
		mockMvc.perform(put("/product/create?name=Product&price=99.99").contentType(APPLICATION_JSON_UTF8).accept(
				MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().string("1"));
	}

	@Test
	public void shouldUpdateProduct() throws Exception {
		// then
		mockMvc.perform(post("/product/update?id=1&name=Product&price=199.99").contentType(
				APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void shouldGetAllProducts() throws Exception {

		// when
		List<ProductDto> list = new ArrayList<>();
		list.add(new ProductDto(1L, "Name", new BigDecimal("99.99")));
		when(productService.getAll()).thenReturn(list);

		String expectedResponse = "[{\"id\":1,\"name\":\"Name\",\"price\":99.99}]";

		// then
		mockMvc.perform(get("/product").contentType(
				APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(expectedResponse));
	}

}