package com.product.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.product.service.impl.OrderServiceImpl;
import com.product.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected OrderServiceImpl orderService;

	@MockBean
	protected ProductServiceImpl productService;

	@Before
	public void setUp() {
		Mockito.reset(orderService, productService);
	}

}