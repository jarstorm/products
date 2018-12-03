package com.product.rest;

public class ProductControllerTest extends AbstractControllerTest {
/*
	@Test
	public void shouldReturnFoundPost() throws Exception {
		// given
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		PostDto post = new PostDto("Title", "content", creationDate);

		// when
		when(postService.getPost(1L)).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.title", is("Title")))
				.andExpect(jsonPath("$.content", is("content")))
				.andExpect(jsonPath("$.creationDate", is(creationDate.toString())));

	}*/
}