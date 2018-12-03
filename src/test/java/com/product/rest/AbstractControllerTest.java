package com.product.rest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	/*@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected OrderService postService;

	@MockBean
	protected ProductService commentService;

	@Before
	public void setUp() {
		Mockito.reset(postService, commentService);
	}*/

}
