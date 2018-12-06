package com.product.rest;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.product.model.Order;
import com.product.model.dto.OrderDto;
import com.product.rest.bean.ProductVo;

/**
 * Test OrderController class.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class OrderControllerTest extends AbstractControllerTest {

	/**
	 * This test should create an order.
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void shouldCreateOrder() throws Exception {
		// Mock order service method
		List<ProductVo> products = new ArrayList<>();
		when(orderService.create("a@a.com", products)).thenReturn(1L);

		// Check REST method
		String content = "{\"userEmail\": \"a@a.com\", \"products\":[]}";
		mockMvc.perform(put("/order/create").content(content).contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().string("1"));
	}

	/**
	 * Test that filter orders it¡s working as expected.
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void filterOrders() throws Exception {
		// Mock service method
		List<OrderDto> list = new ArrayList<>();
		OrderDto order = new OrderDto(1L, Calendar.getInstance(), "a@a.com", new ArrayList<>());
		list.add(order);
		when(orderService.filterOrders(ArgumentMatchers.<Calendar>any(), ArgumentMatchers.<Calendar>any()))
				.thenReturn(list);

		// Check REST method
		mockMvc.perform(get("/order/filter?startDate=20181010101010&endDate=20181010101010")
				.contentType(APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"));
	}

	/**
	 * Calculate order amount.
	 * 
	 * @throws Exception exception
	 */
	@Test
	public void calculateAmount() throws Exception {
		// Mock service method
		Order order = new Order();
		order.setUserEmail("a@a.com");
		when(orderService.calculateAmount(1L)).thenReturn(new BigDecimal("99"));

		// Check REST method
		mockMvc.perform(get("/order/calculateAmount?orderId=1").contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("99"));
	}
}