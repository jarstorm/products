package com.product.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	/*@Autowired
	OrderRepository postRepository;

	@Autowired
	ProductService commentService;

	@Test
	public void shouldAddComment() {
		Order post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setPostId(post.getId());
		comment.setAuthor("Author");
		comment.setContent("Content");
		Long commentId = commentService.addComment(comment);

		assertThat("Comment id shouldn't be null", commentId, notNullValue());
	}

	private Order createTestPost() {
		Order post = new Order();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);
		return post;
	}

	@Test
	public void shouldReturnAddedComment() {
		Order post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setPostId(post.getId());
		comment.setAuthor("Author");
		comment.setContent("Content");

		commentService.addComment(comment);

		List<CommentDto> comments = commentService.getCommentsForPost(post.getId());

		assertThat("There should be one comment", comments, hasSize(1));
		assertThat(comments.get(0).getAuthor(), equalTo("Author"));
		assertThat(comments.get(0).getComment(), equalTo("Content"));
	}*/
}