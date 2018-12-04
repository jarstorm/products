package com.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.model.dto.ProductDto;
import com.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Long createProduct(String name, BigDecimal price) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		productRepository.save(product);
		return product.getId();
	}

	public void updateProduct(Long id, String name, BigDecimal price) throws ProductException {
		Product product = productRepository.getOne(id);
		if (product == null) {
			throw new ProductException("Product with id " + id + " not found");
		}
		product.setName(name);
		product.setPrice(price);
		productRepository.save(product);
	}

	public List<ProductDto> getAll() {
		return productRepository.findAll().parallelStream().map(product -> new ProductDto(product.getId(), product.getName(),
				product.getPrice())).collect(Collectors.toList());
	}

}
