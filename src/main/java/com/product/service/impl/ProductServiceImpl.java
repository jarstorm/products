package com.product.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductException;
import com.product.model.Product;
import com.product.model.dto.ProductDto;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Long createProduct(String name, BigDecimal price) {
		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Price should not be lower than 0");
		}
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product = productRepository.save(product);
		return product.getId();
	}

	@Override
	public void updateProduct(Long id, String name, BigDecimal price) throws ProductException {
		Optional<Product> productOptional = productRepository.findById(id);
		if (!productOptional.isPresent()) {
			throw new ProductException("Product with id " + id + " not found");
		}
		Product product = productOptional.get();
		product.setName(name);
		product.setPrice(price);
		productRepository.save(product);
	}

	@Override
	public List<ProductDto> getAll() {
		return productRepository.findAll().parallelStream().map(product -> new ProductDto(product.getId(), product.getName(),
				product.getPrice())).collect(Collectors.toList());
	}

}
