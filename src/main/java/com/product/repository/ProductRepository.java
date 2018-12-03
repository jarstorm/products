package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	/*@Query("select c from Comment c where c.post.id = :postId")
	List<Product> findByPostId(@Param("postId") Long postId);*/
}
