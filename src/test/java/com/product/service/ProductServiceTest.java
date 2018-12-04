package com.product.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.product.exception.ProductException;
import com.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Before
	public void setUp() {
		Mockito.reset(productService, productRepository);
	}

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private ProductService productService;

	@Test
	public void shouldAddProduct() {
		// when
		Long productId = productService.createProduct("Name", new BigDecimal("99.99"));

		assertThat("Product id shouldn't be null", productId, notNullValue());
	}

	@Test(expected = ProductException.class)
	public void shouldUpdateProduct() throws ProductException {
		// when
		//when(productRepository.getOne(-1L)).thenReturn(null);

		productService.updateProduct(-1L, "Name", new BigDecimal("99.99"));
	}

	/*
	 * @Autowired
	 * OrderRepository postRepository;
	 * private Order createTestPost() {
	 * Order post = new Order();
	 * post.setTitle("Test title");
	 * post.setContent("Test content");
	 * LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
	 * post.setCreationDate(creationDate);
	 * postRepository.save(post);
	 * return post;
	 * }
	 * @Test
	 * public void shouldReturnAddedComment() {
	 * Order post = createTestPost();
	 * NewCommentDto comment = new NewCommentDto();
	 * comment.setPostId(post.getId());
	 * comment.setAuthor("Author");
	 * comment.setContent("Content");
	 * commentService.addComment(comment);
	 * List<CommentDto> comments = commentService.getCommentsForPost(post.getId());
	 * assertThat("There should be one comment", comments, hasSize(1));
	 * assertThat(comments.get(0).getAuthor(), equalTo("Author"));
	 * assertThat(comments.get(0).getComment(), equalTo("Content"));
	 * }
	 */
}