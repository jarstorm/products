package com.product.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	/*@Autowired
	OrderRepository postRepository;

	@Autowired
	OrderService postService;

	@Test
	public void shouldReturnCreatedPost() {
		Order post = new Order();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);

		PostDto postDto = postService.getPost(post.getId());

		assertNotNull("Post shouldn't be null", postDto);
		assertThat(postDto.getContent(), equalTo("Test content"));
		assertThat(postDto.getTitle(), equalTo("Test title"));
		assertThat(postDto.getCreationDate(), equalTo(creationDate));
	}

	@Test
	public void shouldReturnNullForNotExistingPost() {
		PostDto postDto = postService.getPost(123L);

		assertNull(postDto);
	}*/
}